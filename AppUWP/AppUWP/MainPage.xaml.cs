using Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Popups;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento Página en blanco está documentada en https://go.microsoft.com/fwlink/?LinkId=402352&clcid=0xc0a

namespace AppUWP
{
    public sealed partial class MainPage : Page
    {

        public static DatabaseManager db;
        public static ObservableCollection<Ruta> oc_rutes;

        public MainPage()
        {
            this.InitializeComponent();

            db = new DatabaseManager();
            
            //omplo l'arbre de categories
            TreeViewNode root = null;
            PopulateArbre(ref root, db.AgafarCategories());

            cbCategories.ItemsSource = db.AgafarCategoriesAmbRuta();

            //omplo la llista de rutes
            oc_rutes = new ObservableCollection<Ruta> (db.AgafarRutes());
            listViewRutes.ItemsSource = oc_rutes;
        }


        public void PopulateArbre(ref TreeViewNode root, List<Categoria> categories)
        {
            if (root == null)
            {
                root = new TreeViewNode();
                root.Content = "Categorias";
                arbre.RootNodes.Add(root);

                List<Categoria> sense_pares = categories.Where(t => t.Pare == null).ToList();
                foreach (Categoria c in sense_pares)
                {
                    TreeViewNode n = new TreeViewNode();
                    n.Content = c.Nom;
                    PopulateArbre(ref n, categories);
                    root.Children.Add(n);
                }
            }
            else
            {
                string nomPare = root.Content.ToString();
                Categoria catPare = categories.Find(t => t.Nom == nomPare);
                List<Categoria> fills = categories.Where(t => t.Pare == catPare).ToList();
                foreach (Categoria fill in fills)
                {
                    TreeViewNode n = new TreeViewNode();
                    n.Content = fill.Nom;
                    PopulateArbre(ref n, categories);
                    root.Children.Add(n);
                }
            }
        }

        private void btn_clk_add(object sender, RoutedEventArgs e)
        {
            this.Frame.Navigate(typeof(DetallPage), nomCatSelected);
        }

        private void btn_clk_mod(object sender, RoutedEventArgs e)
        {
            this.Frame.Navigate(typeof(DetallPage), listViewRutes.SelectedItem);
        }

        private async void btn_clk_rem(object sender, RoutedEventArgs e)
        {
            MessageDialog md = new MessageDialog("Estàs segur que vols esborrar la ruta? També esborraràs els seus punts.");

            md.Commands.Add(new UICommand("Si, n'estic segur", new UICommandInvokedHandler(this.CommandInvokedHandler)));
            md.Commands.Add(new UICommand("No", new UICommandInvokedHandler(this.CommandInvokedHandler)));

            md.DefaultCommandIndex = 0;
            md.CancelCommandIndex = 1;

            await md.ShowAsync();

        }

        private void CommandInvokedHandler(IUICommand command)
        {
            if("Si, n'estic segur" == command.Label)
            {
                Ruta r = (Ruta)listViewRutes.SelectedItem;
                Debug.WriteLine("AQUI INVOCARIA EL METODE DELETE RUTA" + r.Id);
                //remove item del itemSource de listView
            }
        }

        private void btn_clk_clear(object sender, RoutedEventArgs e)
        {
            filtreRuta.Text = "";
            arbre.SelectionMode = TreeViewSelectionMode.None;
            arbre.SelectionMode = TreeViewSelectionMode.Single;
            nomCatSelected = null;

            listViewRutes.ItemsSource = oc_rutes;
        }

        public String nomCatSelected;

        private void OnSelectionChanged(TreeView sender, TreeViewItemInvokedEventArgs args)
        {
            if (args.InvokedItem is TreeViewNode node && node.Content is String nomCategoria)
            {
                if (node.Depth == 0) return;
                nomCatSelected = nomCategoria;
                filtrar();
            }
        }

        private void filtreRuta_change(TextBox sender, TextBoxTextChangingEventArgs args)
        {
            filtrar();
        }

        private void filtrar()
        {
            string filtreText = filtreRuta.Text.ToLower();
            if (nomCatSelected != null)
            {
                listViewRutes.ItemsSource = oc_rutes.Where(s => s.Titol.ToLower().Contains(filtreText) && s.Categoria.Nom == nomCatSelected).ToList();
            }
            else
            {
                listViewRutes.ItemsSource = oc_rutes.Where(s => s.Titol.ToLower().Contains(filtreText)).ToList();
            }
        }

        private void btn_clk_report(object sender, RoutedEventArgs e)
        {
            Categoria c = (Categoria)cbCategories.SelectedItem;
            Debug.WriteLine("CAT="+c.ToString());
            this.Frame.Navigate(typeof(ReportPage), c);
        }
    }
}
