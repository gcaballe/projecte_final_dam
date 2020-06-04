using Model;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento Página en blanco está documentada en https://go.microsoft.com/fwlink/?LinkId=234238

namespace AppUWP
{
    /// <summary>
    /// Una página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class ReportPage : Page
    {
        private Categoria _c;

        public ReportPage()
        {
            this.InitializeComponent();

           

            
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            _c = e.Parameter as Categoria;

            //int id_cat = 2;
            int id_cat = _c.Id;

            string url = "http://iguixa.hopto.org:5050/jasperserver/flow.html?_flowId=viewReportFlow&_flowId=viewReportFlow&ParentFolderUri=%2Fm2-gcaballe&reportUnit=%2Fm2-gcaballe%2Freport_rutes&standAlone=true&param_id_categoria=";
            url += id_cat.ToString();

            wvReport.Navigate(new Uri(url));
        }

        private void btnBack_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(MainPage));
        }
    }
}
