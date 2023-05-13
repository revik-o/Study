using Npgsql;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Kursach
{
    public partial class User : Form
    {
        private NpgsqlConnection PGconnection;

        public User()
        {
            InitializeComponent();

        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                SQLquery(String.Format("SELECT public.\"InsertClient\"('{0}', '{1}', '{2}', '{3}')",
                    textBox1.Text, textBox2.Text, textBox3.Text, textBox4.Text)
                );
                string str = SQLquerySTR("SELECT id FROM public.\"Client\" WHERE surname='" + textBox1.Text + "'");
                int ind1 = Int32.Parse(str);
                str = SQLquerySTR("SELECT id FROM public.\"RoomNumber\" WHERE \"roomNumber\"='" + comboBox2.Text + "'");
                int ind2 = Int32.Parse(str);
                str = SQLquerySTR("SELECT AServ.id FROM public.\"AdditionalServices\" AServ, public.\"Food\" F WHERE F.id=\"idFood\" AND F.type='" + comboBox3.Text + "'");
                int ind3 = Int32.Parse(str);
                SQLquery(String.Format("SELECT public.\"InsertRecreationBase\"({0}, {1}, '{2}', '{3}', {4})",
                    ind1, ind2, dateTimePicker1.Value, dateTimePicker2.Value, ind3)
                );
                MessageBox.Show("Номер заброньовано");
                textBox1.Text = "";
                textBox2.Text = "";
                textBox3.Text = "";
                textBox4.Text = "";
                comboBox2.Text = "";
                comboBox3.Text = "";
                dateTimePicker1.Value = DateTime.Today;
                dateTimePicker2.Value = DateTime.Today;
                SQLforCB(comboBox2, "SELECT \"roomNumber\" FROM public.\"RoomNumber\" WHERE \"idRoomStatus\"=5");
                SQLforCB(comboBox3, "SELECT type, \"typeSt\" FROM public.\"AdditionalServicesView\"");
            }
            catch(Exception E) { MessageBox.Show("Не вдалося забронювати номер"); }
        }

        private void User_Shown(object sender, EventArgs e)
        {
            try
            {
                string connection = String.Format(
                    "Server={0};Username={1};Database={2};Port={3};Password={4};SSLMode=Prefer;",
                    "127.0.0.1", "postgres", "RecreationCenter", 5432, "12345678"
                );
                PGconnection = new NpgsqlConnection(connection);

                SQLforCB(comboBox2, "SELECT \"roomNumber\" FROM public.\"RoomNumber\" WHERE \"idRoomStatus\"=5");
                SQLforCB(comboBox3, "SELECT type, \"typeSt\" FROM public.\"AdditionalServicesView\"");
            }
            catch (Exception E) { MessageBox.Show("Не вдалось підключити базу данних"); }
        }

        private void SQLquery(string query)
        {
            PGconnection.Open();

            NpgsqlCommand command = new NpgsqlCommand(query, PGconnection);
            command.ExecuteNonQuery();

            PGconnection.Close();
        }

        private string SQLquerySTR(string query)
        {
            PGconnection.Open();

            NpgsqlCommand command = new NpgsqlCommand(query, PGconnection);
            NpgsqlDataReader dataReade = command.ExecuteReader();
            string str1 = null;
            if (dataReade.Read())
                str1 = dataReade.GetValue(0).ToString();
            PGconnection.Close();
            return str1;
        }

        private void SQLforCB(ComboBox comboBox, string query)
        {
            PGconnection.Open();

            NpgsqlCommand command = new NpgsqlCommand(query, PGconnection);
            NpgsqlDataReader dataReader = command.ExecuteReader();

            List<string> list = new List<string>();

            while (dataReader.Read())
            {
                list.Add(dataReader.GetValue(0).ToString());
            }

            foreach (string str in list)
            {
                comboBox.Items.Add(str);
            }

            PGconnection.Close();
        }

        private void User_FormClosed(object sender, FormClosedEventArgs e)
        {
            Application.Exit();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            try { 
                MessageBox.Show(SQLquerySTR("SELECT public.\"NumberOfFreePlaces\"()"));
            }catch(Exception E) { MessageBox.Show("Не вдалося виконати запит"); }
        }
    }
}
