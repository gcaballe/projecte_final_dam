using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model
{
    public class Punt
    {

        private int numero;
        private String nom;
        private String descripcio;
        private int hora;
        private double latitud;
        private double longitud;
        private int elevació;
        private String photoURL;
        private String photoTitol;
        private int ruta;

        public Punt(int numero, string nom, string descripcio, int hora, double latitud, double longitud, int elevació, string photoURL, string photoTitol, int ruta)
        {
            Numero = numero;
            Nom = nom;
            Descripcio = descripcio;
            Hora = hora;
            Latitud = latitud;
            Longitud = longitud;
            Elevació = elevació;
            PhotoURL = photoURL;
            PhotoTitol = photoTitol;
            Ruta = ruta;
        }

        public override String ToString()
        {
            return Numero + ". " + Nom;
        }

        public int Numero { get => numero; set => numero = value; }
        public string Nom { get => nom; set => nom = value; }
        public string Descripcio { get => descripcio; set => descripcio = value; }
        public int Hora { get => hora; set => hora = value; }
        public double Latitud { get => latitud; set => latitud = value; }
        public double Longitud { get => longitud; set => longitud = value; }
        public int Elevació { get => elevació; set => elevació = value; }
        public string PhotoURL { get => photoURL; set => photoURL = value; }
        public string PhotoTitol { get => photoTitol; set => photoTitol = value; }
        public int Ruta { get => ruta; set => ruta = value; }
    }
}
