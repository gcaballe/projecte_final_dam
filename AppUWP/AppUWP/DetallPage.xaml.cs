using FtpLlibreria;
using Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.Storage;
using Windows.Storage.Pickers;
using Windows.Storage.Streams;
using Windows.UI;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Media.Imaging;
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento Página en blanco está documentada en https://go.microsoft.com/fwlink/?LinkId=234238

namespace AppUWP
{
    /// <summary>
    /// Una página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class DetallPage : Page
    {
        private Boolean esticCreant;
        private Ruta _ruta;
        private List<Punt> _punts;
        private ObservableCollection<Punt> _ocPunts;

        private string url_apache = "http://192.168.1.217/projecte_final_dam/";

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);

            //o bé modifico
            esticCreant = false;
            //MODIFICO RUTA = 1
            int ruta_id = 1;

            _ruta = MainPage.db.AgafarRuta(ruta_id);

            Populate_camps_ruta();

            _punts = MainPage.db.AgafarPunts(_ruta);
            _ocPunts = new ObservableCollection<Punt>(_punts);

            lvPunts.ItemsSource = _ocPunts;
            





            //o bé creo una ruta nova



        }

        private void Populate_camps_ruta()
        {
            tbTitolRuta.Text = _ruta.Titol;

            tbDescMarkDown.Text = _ruta.DescMarkDown;

            tbDesnivell.Text = _ruta.Desnivell.ToString();

            int min = _ruta.TempsAprox % 60;
            int hora = _ruta.TempsAprox / 60;
            tbTempsAprox.Text = hora + "h " + min + "m";

            tbAlcadaMax.Text = _ruta.AlçadaMax.ToString();

            tbAlcadaMin.Text = _ruta.AlcadaMin.ToString();

            tbDistanciaKm.Text = _ruta.DistanciaKm.ToString();

            cbCircular.IsChecked = _ruta.Circular;

            rcDificultat.Value = _ruta.Dificultat;

            cbCategories.ItemsSource = new ObservableCollection<Categoria> (MainPage.db.AgafarCategories());

            //falta fotopicker
            
            string url = url_apache + _ruta.PhotoURL;
            imageFotoRuta.Source = new BitmapImage(new Uri(url));
            imageFotoRuta.Tapped += ImageFotoRuta_Tapped;

            tbTitolFoto.Text = _ruta.PhotoTitol;

            //falta gpx picker

        }

        private void ImageFotoRuta_Tapped(object sender, TappedRoutedEventArgs e)
        {
            obrir_filePicker();
        }

        private async System.Threading.Tasks.Task obrir_filePicker()
        {

            FileOpenPicker openPicker = new FileOpenPicker();
            openPicker.ViewMode = PickerViewMode.Thumbnail;
            openPicker.SuggestedStartLocation = PickerLocationId.Downloads;
            openPicker.FileTypeFilter.Add(".jpg");
            openPicker.FileTypeFilter.Add(".jpeg");
            openPicker.FileTypeFilter.Add(".png");

            
            StorageFile file = await openPicker.PickSingleFileAsync();
            if (file != null)
            {
                byte[] byte_array = await GetBytesAsync(file);

                //pic
                FTPHelper.PujarAFTP(byte_array);
            }
        }

        public DetallPage()
        {
            this.InitializeComponent();
        }

        private void cambiarDificultat(RatingControl sender, object args)
        {

        }

        private void TbDescMarkDown_SelectionChanged(object sender, RoutedEventArgs e)
        {
            mtbDescMarkDown.Text = tbDescMarkDown.Text;
        }


        public static async Task<byte[]> GetBytesAsync(StorageFile file)
        {
            byte[] fileBytes = null;
            if (file == null) return null;
            using (var stream = await file.OpenReadAsync())
            {
                fileBytes = new byte[stream.Size];
                using (var reader = new DataReader(stream))
                {
                    await reader.LoadAsync((uint)stream.Size);
                    reader.ReadBytes(fileBytes);
                }
            }
            return fileBytes;
        }






        private Boolean valid1 = true, valid2 = true, valid3 = true, valid4 = true, valid5 = true, valid6 = true, valid7 = true;
        SolidColorBrush vermell = new SolidColorBrush(Colors.Red);
        SolidColorBrush blanc = new SolidColorBrush(Colors.White);
        Regex regex_digits = new Regex(@"^\d*$");
        Regex regex_tempsAprox = new Regex(@"^\d*h \d{2}m$");

        private void TbTitolRuta_TextChanged(object sender, TextChangedEventArgs e)
        {
            String s = ((TextBox)sender).Text;
            int count = MainPage.oc_rutes.Where(t => t.Titol == s).Count();
            if (s != "" && count < 1) //aixo fa que al començament sigui vermell
            {
                valid1 = true;
                ((TextBox)sender).Background = blanc;
            }
            else
            {
                valid1 = false;
                ((TextBox)sender).Background = vermell;
            }
            calcularApplyChanges();
        }

        private void TbDescMarkDown_TextChanged(object sender, TextChangedEventArgs e)
        {
            if (((TextBox)sender).Text != "")
            {
                valid2 = true;
                ((TextBox)sender).Background = blanc;
            }
            else
            {
                valid2 = false;
                ((TextBox)sender).Background = vermell;
            }
            calcularApplyChanges();
        }

        private void TbDesnivell_TextChanged(object sender, TextChangedEventArgs e)
        {
            if (regex_digits.IsMatch(((TextBox)sender).Text) && ((TextBox)sender).Text != "")
            {
                valid3 = true;
                ((TextBox)sender).Background = blanc;
            }
            else
            {
                valid3 = false;
                ((TextBox)sender).Background = vermell;
            }
            calcularApplyChanges();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btnBack_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(MainPage));
        }

        private void TbTempsAprox_TextChanged(object sender, TextChangedEventArgs e)
        {
            if (regex_tempsAprox.IsMatch(((TextBox)sender).Text) && ((TextBox)sender).Text != "")
            {
                valid4 = true;
                ((TextBox)sender).Background = blanc;
            }
            else
            {
                valid4 = false;
                ((TextBox)sender).Background = vermell;
            }
            calcularApplyChanges();
        }

        private void TbAlcadaMin_TextChanged(object sender, TextChangedEventArgs e)
        {
            if (regex_digits.IsMatch(((TextBox)sender).Text) && ((TextBox)sender).Text != "")
            {
                valid5 = true;
                ((TextBox)sender).Background = blanc;
            }
            else
            {
                valid5 = false;
                ((TextBox)sender).Background = vermell;
            }
            calcularApplyChanges();
        }

        private void TbAlcadaMax_TextChanged(object sender, TextChangedEventArgs e)
        {
            if (regex_digits.IsMatch(((TextBox)sender).Text) && ((TextBox)sender).Text != "")
            {
                valid6 = true;
                ((TextBox)sender).Background = blanc;
            }
            else
            {
                valid6 = false;
                ((TextBox)sender).Background = vermell;
            }
            calcularApplyChanges();
        }

        private void TbDistanciaKm_TextChanged(object sender, TextChangedEventArgs e)
        {
            if (regex_digits.IsMatch(((TextBox)sender).Text) && ((TextBox)sender).Text != "")
            {
                valid7 = true;
                ((TextBox)sender).Background = blanc;
            }
            else
            {
                valid7 = false;
                ((TextBox)sender).Background = vermell;
            }
            calcularApplyChanges();
        }

        private void calcularApplyChanges()
        {
            Boolean resultat = (valid1 && valid2 && valid3 && valid4 && valid5 && valid6 && valid7);
            
            btn_ApplyChanges.IsEnabled = resultat;
        }

        

        private void Btn_ApplyChanges_Click(object sender, RoutedEventArgs e)
        {
            Debug.WriteLine("CLICK APPLYCHANGES");
            if (esticCreant)
            {
                //insert


            }
            else
            {
                //update
                //creo Ruta r
                //update(r)

            }
        }

    }


}


