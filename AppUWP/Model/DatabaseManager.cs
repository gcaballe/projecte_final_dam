using System;
using System.Collections.Generic;
using MySql.Data.MySqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;
using System.Data;

namespace Model
{
    public class DatabaseManager
    {
        public MySqlConnection mysqlcon;

        public DatabaseManager()
        {
            Debug.WriteLine("Start of mysql connect test");
            //string M_str_sqlcon = "server=127.0.0.1;user id=guillem;password=guillem;database=projecte_final_dam";
            string M_str_sqlcon = "server=iguixa.hopto.org;Port=4040;user id=m2-gcaballe;password=47105665D;database=m2_gcaballe";
            mysqlcon = new MySqlConnection(M_str_sqlcon);

            
        }

        public List<Categoria> AgafarCategories()
        {
            List<Categoria> l = new List<Categoria>();
            MySqlCommand mysqlcom = new MySqlCommand("select id, nom, pare from categoria order by id", mysqlcon);
            mysqlcon.Open();
            MySqlDataReader mysqlread = mysqlcom.ExecuteReader(CommandBehavior.CloseConnection);
            while (mysqlread.Read())
            {
                Categoria c = new Categoria(mysqlread.GetInt32(0), mysqlread.GetString(1),null);
                if (mysqlread.IsDBNull(2)) c.Pare_aux = -1;
                else c.Pare_aux = mysqlread.GetInt32(2);
                l.Add(c);
            }

            foreach(Categoria c in l)
            {
                if(c.Pare_aux != -1)
                {
                    c.Pare = l.Find(x => x.Id == c.Pare_aux);
                }
            }
            
            mysqlcon.Close();

            return l;
        }

        public List<Ruta> AgafarRutes()
        {
            List<Ruta> l = new List<Ruta>();
            MySqlCommand mysqlcom = new MySqlCommand("SELECT r.*, f.titol as foto_titol, f.url as foto_url, c.id as cat_id, c.nom as cat_nom " +
                "FROM ruta r JOIN foto f on (r.foto = f.url) JOIN categoria c on (r.categoria = c.id)", mysqlcon);
            mysqlcon.Open();
            MySqlDataReader mysqlread = mysqlcom.ExecuteReader(CommandBehavior.CloseConnection);
            while (mysqlread.Read())
            {
                Ruta r = new Ruta(mysqlread.GetInt32(0),
                                  mysqlread.GetString(1),
                                  mysqlread.GetString(2),
                                  mysqlread.GetInt32(3),
                                  mysqlread.GetInt32(4),
                                  mysqlread.GetInt32(5),
                                  mysqlread.GetFloat(6),
                                  mysqlread.GetInt32(7),
                                  mysqlread.GetBoolean(8),
                                  mysqlread.GetInt32(9),
                                  mysqlread.GetString(10),
                                  mysqlread.GetString(14),
                                  mysqlread.GetString(13),

                                  new Categoria(mysqlread.GetInt32(15), mysqlread.GetString(16), null));

                l.Add(r);
            }
            mysqlcon.Close();

            return l;
        }


        public Ruta AgafarRuta(int ruta_id)
        {
            Ruta r = null;
            MySqlCommand mysqlcom = new MySqlCommand("SELECT r.*, f.titol as foto_titol, f.url as foto_url, c.id as cat_id, c.nom as cat_nom " +
                "FROM ruta r JOIN foto f on (r.foto = f.url) JOIN categoria c on (r.categoria = c.id) " +
                "WHERE r.id = " + ruta_id, mysqlcon);

            mysqlcon.Open();
            MySqlDataReader mysqlread = mysqlcom.ExecuteReader(CommandBehavior.CloseConnection);
            while (mysqlread.Read())
            {
                r = new Ruta(mysqlread.GetInt32(0),
                                    mysqlread.GetString(1),
                                    mysqlread.GetString(2),
                                    mysqlread.GetInt32(3),
                                    mysqlread.GetInt32(4),
                                    mysqlread.GetInt32(5),
                                    mysqlread.GetFloat(6),
                                    mysqlread.GetInt32(7),
                                    mysqlread.GetBoolean(8),
                                    mysqlread.GetInt32(9),
                                    mysqlread.GetString(10),
                                    mysqlread.GetString(14),
                                    mysqlread.GetString(13),

                                    new Categoria(mysqlread.GetInt32(15), mysqlread.GetString(16), null));
            }
            mysqlcon.Close();
            return r;
        }

        public List<Punt> AgafarPunts(Ruta r)
        {
            List<Punt> llista = new List<Punt>();
            MySqlCommand mysqlcom = new MySqlCommand("SELECT p.*, f.titol as foto_titol, f.url as foto_url " +
                "FROM punt p JOIN foto f on (p.foto = f.url) WHERE p.ruta = " + r.Id + " ORDER BY p.numero" , mysqlcon);
            mysqlcon.Open();
            MySqlDataReader mysqlread = mysqlcom.ExecuteReader(CommandBehavior.CloseConnection);
            while (mysqlread.Read())
            {
                Punt p = new Punt(mysqlread.GetInt32(0),
                                    mysqlread.GetString(1),
                                    mysqlread.GetString(2),
                                    mysqlread.GetInt32(3),
                                    mysqlread.GetDouble(4),
                                    mysqlread.GetDouble(5),
                                    mysqlread.GetInt32(6),
                                    mysqlread.GetString(10),
                                    mysqlread.GetString(9),
                                    mysqlread.GetInt32(8));

                llista.Add(p);
            }
            mysqlcon.Close();

            return llista;
        }



        //auxiliar, pel comboBox del report
        public List<Categoria> AgafarCategoriesAmbRuta()
        {
            List<Categoria> l = new List<Categoria>();
            MySqlCommand mysqlcom = new MySqlCommand("select distinct c.id, c.nom, c.pare from categoria c "
                + "JOIN ruta r ON(r.categoria = c.id) order by c.id", mysqlcon);
            mysqlcon.Open();
            MySqlDataReader mysqlread = mysqlcom.ExecuteReader(CommandBehavior.CloseConnection);
            while (mysqlread.Read())
            {
                Categoria c = new Categoria(mysqlread.GetInt32(0), mysqlread.GetString(1), null);
                l.Add(c);
            }

            mysqlcon.Close();

            return l;
        }

    }
}
