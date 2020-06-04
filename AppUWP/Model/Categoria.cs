using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model
{
    public class Categoria
    {
        private int id;
        private String nom;
        private Categoria pare;
        private int pare_aux;

        public int Id { get => id; set => id = value; }
        public string Nom { get => nom; set => nom = value; }
        public Categoria Pare { get => pare; set => pare = value; }
        public int Pare_aux { get => pare_aux; set => pare_aux = value; }

        public Categoria(int id, String nom, Categoria pare)
        {
            this.Id = id;
            this.Nom = nom;
            this.pare = pare;
        }
        
        public override String ToString()
        {
            return Nom;
        }
       

    }
}
