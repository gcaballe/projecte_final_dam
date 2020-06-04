using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model
{
    public class Ruta
    {
        private int id;
        private String titol;
        private String descMarkDown;
        private int desnivell;
        private int alçadaMax;
        private int alcadaMin;
        private float distanciaKm;
        private int tempsAprox;
        private Boolean circular;
        private int dificultat;
        private String gpxURL;
        private String photoURL;
        private String photoTitol;

        private Categoria categoria;

        public int Id { get => id; set => id = value; }
        public string Titol { get => titol; set => titol = value; }
        public string DescMarkDown { get => descMarkDown; set => descMarkDown = value; }
        public int Desnivell { get => desnivell; set => desnivell = value; }
        public int AlçadaMax { get => alçadaMax; set => alçadaMax = value; }
        public int AlcadaMin { get => alcadaMin; set => alcadaMin = value; }
        public float DistanciaKm { get => distanciaKm; set => distanciaKm = value; }
        public int TempsAprox { get => tempsAprox; set => tempsAprox = value; }
        public bool Circular { get => circular; set => circular = value; }
        public int Dificultat { get => dificultat; set => dificultat = value; }
        public string GpxURL { get => gpxURL; set => gpxURL = value; }
        public string PhotoURL { get => photoURL; set => photoURL = value; }
        public string PhotoTitol { get => photoTitol; set => photoTitol = value; }
        public Categoria Categoria { get => categoria; set => categoria = value; }

        public Ruta(int id, string titol, string descMarkDown, int desnivell, int alçadaMax, int alcadaMin, float distanciaKm, int tempsAprox, bool circular, int dificultat, string gpxURL, string photoURL, string photoTitol, Categoria categoria)
        {
            Id = id;
            Titol = titol;
            DescMarkDown = descMarkDown;
            Desnivell = desnivell;
            AlçadaMax = alçadaMax;
            AlcadaMin = alcadaMin;
            DistanciaKm = distanciaKm;
            TempsAprox = tempsAprox;
            Circular = circular;
            Dificultat = dificultat;
            GpxURL = gpxURL;
            PhotoURL = photoURL;
            PhotoTitol = photoTitol;
            Categoria = categoria;
        }

        override
        public String ToString()
        {
            return Id + ". " + Titol;
        }
    }
}
