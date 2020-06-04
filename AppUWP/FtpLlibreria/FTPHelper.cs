using FluentFTP;
using System;
using System.Diagnostics;
using System.IO;
using System.Net;

namespace FtpLlibreria
{
    public class FTPHelper
    {
        public static void PujarAFTP(byte[] param_byte_array)
        {
            FtpClient client = new FtpClient();
            //WebClient webClient = new WebClient();
            client.Host = "192.168.1.217";
            client.Credentials = new NetworkCredential("guillem", "");
            
            client.Connect();
            Debug.WriteLine("Conectat!");

            //webClient.Credentials = new NetworkCredential("guillem", "");


            foreach ( var f in client.GetListing())
            {
                Debug.Write("YOYOYO" + f.ToString());
            }

            // upload a file
            //client.UploadFile(@"C:/Users/guillem/Downloads/A820.pdf", "26/big.pdf", FtpRemoteExists.Overwrite, true);

            
            string fileName = "test.pdf";

            //client.Upload(param_byte_array, uri.LocalPath + "/" + fileName);
            //ftpsClient.Disconnect();
        }
    }
}
