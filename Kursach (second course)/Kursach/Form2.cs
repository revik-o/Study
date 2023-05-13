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
    public partial class Form2 : Form
    {
        private NpgsqlConnection PGconnection;
        private string SQLRecreationBase;
        private string SQLClient;
        private string SQLRoomNumber;
        private string SQLComfort;
        private string SQLRoomStatus;
        private string SQLAdditionalServices;
        private string SQLFood;
        private string SQLSpaTreatments;
        private int TableIndex;
        private string TabSTR;

        private void SQLquery(string query)
        {
            PGconnection.Open();
            try { 
                NpgsqlCommand command = new NpgsqlCommand(query, PGconnection);
                command.ExecuteNonQuery();
            }catch (Exception E) { MessageBox.Show("Неможливо виконати запит"); }

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

        private void SQLforTable(DataGridView dataGridView, string query)
        {
            PGconnection.Open();

            NpgsqlCommand command = new NpgsqlCommand(query, PGconnection);
            NpgsqlDataReader dataReader = command.ExecuteReader();

            List<string[]> list = new List<string[]>();

            string[] strBUF = new string[dataGridView.Columns.Count];

            while (dataReader.Read())
            {
                list.Add(new string[dataGridView.Columns.Count]);
                for (int i = 0; i < dataGridView.Columns.Count; i++)
                {
                    list[list.Count - 1][i] = dataReader.GetValue(i).ToString();
                }
            }

            foreach (string[] str in list)
            {
                dataGridView.Rows.Add(str);
            }

            PGconnection.Close();
        }

        private void FillComboBox(ComboBox comboBox, DataGridView dataGrid, string Cells)
        {
            string[] buf = new string[dataGrid.Rows.Count - 1];
            int gg = 0;
          
            bool bbb = false; string d = null;

            for (int i = 0; i < dataGrid.Rows.Count - 1; i++)
            {
                for (int j = 0; j < buf.Length; j++)
                {
                    d = dataGrid.Rows[i].Cells[Cells].Value.ToString();
                    if (buf[j] == d)
                    {
                        bbb = false;
                        break;
                    }
                    else if (buf[j] != d)
                    {
                        d = dataGrid.Rows[i].Cells[Cells].Value.ToString();
                        bbb = true;
                    }
                }
                if(bbb == true)
                    buf[i] = d;
            }

            for (int i = 0; i < buf.Length; i++)
            {
                if(buf[i] != "" && buf[i] != null)
                {
                    comboBox.Items.Add(buf[i]);
                }
                else {}
            }
        }

        public Form2()
        {
            //dataGridView1
            SQLRecreationBase = "SELECT * FROM public.\"RecreationBaseView\"";
            //dataGridView2
            SQLClient = "SELECT * FROM public.\"Client\"";
            //dataGridView3
            SQLRoomNumber = "SELECT * FROM public.\"RoomNumberView\"";
            //dataGridView4
            SQLComfort = "SELECT * FROM public.\"Comfort\"";
            //dataGridView5
            SQLRoomStatus = "SELECT * FROM public.\"RoomStatus\"";
            //dataGridView6
            SQLAdditionalServices = "SELECT * FROM public.\"AdditionalServicesView\"";
            //dataGridView7
            SQLFood = "SELECT * FROM public.\"Food\"";
            //dataGridView8
            SQLSpaTreatments = "SELECT * FROM public.\"SpaTreatments\"";

            InitializeComponent();
            //RecreationBase
            dataGridView1.Columns.Add("id", "ID");
            dataGridView1.Columns[0].Visible = false;
            dataGridView1.Columns.Add("surname", "Прізвище");
            dataGridView1.Columns.Add("name", "Ім'я");
            dataGridView1.Columns.Add("patronymic", "По батькові");
            dataGridView1.Columns.Add("phone", "Телефон");
            dataGridView1.Columns.Add("idRoomNumber", "Номер");
            dataGridView1.Columns.Add("startDate", "Дата початку");
            dataGridView1.Columns.Add("endDate", "Дата кінця");
            dataGridView1.Columns.Add("idAdditionalServicesFOOD", "Харчування");
            dataGridView1.Columns.Add("idAdditionalServicesST", "Спа Процедури");
            //Client
            dataGridView2.Columns.Add("id", "ID");
            dataGridView2.Columns[0].Visible = false;
            dataGridView2.Columns.Add("surname", "Прізвище");
            dataGridView2.Columns.Add("name", "Ім'я");
            dataGridView2.Columns.Add("patronymic", "По батькові");
            dataGridView2.Columns.Add("phone", "Телефон");
            //RoomNumber
            dataGridView3.Columns.Add("id", "ID");
            dataGridView3.Columns[0].Visible = false;
            dataGridView3.Columns.Add("roomNumber", "Номер кімнати");
            dataGridView3.Columns.Add("numberOfSeats", "Кількість місць");
            dataGridView3.Columns.Add("price", "Ціна");
            dataGridView3.Columns.Add("idComfort", "Комфортність");
            dataGridView3.Columns.Add("idRoomStatus", "Статус Номера");
            //Comfort
            dataGridView4.Columns.Add("id", "ID");
            dataGridView4.Columns[0].Visible = false;
            dataGridView4.Columns.Add("type", "Тип");
            //RoomStatus
            dataGridView5.Columns.Add("id", "ID");
            dataGridView5.Columns[0].Visible = false;
            dataGridView5.Columns.Add("type", "Тип");
            //AdditionalServices
            dataGridView6.Columns.Add("id", "ID");
            dataGridView6.Columns[0].Visible = false;
            dataGridView6.Columns.Add("idFood", "Харчування");
            dataGridView6.Columns.Add("idSpaTreatments", "Спа процедури");
            //Food
            dataGridView7.Columns.Add("id", "ID");
            dataGridView7.Columns[0].Visible = false;
            dataGridView7.Columns.Add("type", "Тип");
            //SpaTreatments
            dataGridView8.Columns.Add("id", "ID");
            dataGridView8.Columns[0].Visible = false;
            dataGridView8.Columns.Add("type", "Тип");

            dateTimePicker1.Value = DateTime.Today;
            dateTimePicker2.Value = DateTime.Today;
        }

        private void Form2_FormClosed(object sender, FormClosedEventArgs e)
        {
            Application.Exit();
        }

        private void Form2_Shown(object sender, EventArgs e)
        {
           try
           {
                string connection = String.Format(
                    "Server={0};Username={1};Database={2};Port={3};Password={4};SSLMode=Prefer;",
                    "127.0.0.1", "postgres", "RecreationCenter", 5432, "12345678"
                );
                PGconnection = new NpgsqlConnection(connection);

                UpdateINFO();
           }
           catch (Exception E) { MessageBox.Show("Не вдалось підключити базу данних"); }
        }

        private void UpdateINFO()
        {
            dataGridView1.Rows.Clear();
            dataGridView2.Rows.Clear();
            dataGridView3.Rows.Clear();
            dataGridView4.Rows.Clear();
            dataGridView5.Rows.Clear();
            dataGridView6.Rows.Clear();
            dataGridView7.Rows.Clear();
            dataGridView8.Rows.Clear();

            comboBox1.Items.Clear();
            comboBox2.Items.Clear();
            comboBox3.Items.Clear();
            comboBox4.Items.Clear();
            comboBox5.Items.Clear();
            comboBox6.Items.Clear();
            comboBox7.Items.Clear();

            SQLforTable(dataGridView1, SQLRecreationBase);
            SQLforTable(dataGridView2, SQLClient);
            SQLforTable(dataGridView3, SQLRoomNumber);
            SQLforTable(dataGridView4, SQLComfort);
            SQLforTable(dataGridView5, SQLRoomStatus);
            SQLforTable(dataGridView6, SQLAdditionalServices);
            SQLforTable(dataGridView7, SQLFood);
            SQLforTable(dataGridView8, SQLSpaTreatments);

            FillComboBox(comboBox1, dataGridView2, "surname");
            FillComboBox(comboBox2, dataGridView3, "roomNumber");
            FillComboBox(comboBox3, dataGridView6, "idFood");
            FillComboBox(comboBox4, dataGridView4, "type");
            FillComboBox(comboBox5, dataGridView5, "type");
            FillComboBox(comboBox6, dataGridView7, "type");
            FillComboBox(comboBox7, dataGridView8, "type");
        }

        //RecreationBase
        private void button25_Click(object sender, EventArgs e)
        {
            comboBox1.Text = "";
            comboBox2.Text = "";
            dateTimePicker1.Value = DateTime.Today;
            dateTimePicker2.Value = DateTime.Today;
            comboBox3.Text = "";
        }
        //Client
        private void button26_Click(object sender, EventArgs e)
        {
            textBox1.Text = "";
            textBox2.Text = "";
            textBox3.Text = "";
            textBox4.Text = "";
        }
        //RoomNumber
        private void button27_Click(object sender, EventArgs e)
        {
            textBox5.Text = "";
            textBox6.Text = "";
            textBox7.Text = "";
            comboBox4.Text = "";
            comboBox5.Text = "";
        }
        //Comfort
        private void button28_Click(object sender, EventArgs e)
        {
            textBox8.Text = "";
        }
        //RoomStatus
        private void button29_Click(object sender, EventArgs e)
        {
            textBox9.Text = "";
        }
        //AdditionalServices
        private void button30_Click(object sender, EventArgs e)
        {
            comboBox6.Text = "";
            comboBox7.Text = "";
        }
        //Food
        private void button31_Click(object sender, EventArgs e)
        {
            textBox10.Text = "";
        }
        //SpaTreatments
        private void button32_Click(object sender, EventArgs e)
        {
            textBox11.Text = "";
        }
        
        //RecreationBase
        private void button1_Click(object sender, EventArgs e)
        {
            if (comboBox1.Text != "" && comboBox2.Text != "" && comboBox3.Text != "")
            {
                string str = SQLquerySTR("SELECT id FROM public.\"Client\" WHERE surname='" + comboBox1.Text + "'");
                int ind1 = Int32.Parse(str);
                str = SQLquerySTR("SELECT id FROM public.\"RoomNumber\" WHERE \"roomNumber\"='" + comboBox2.Text + "'");
                int ind2 = Int32.Parse(str);
                str = SQLquerySTR("SELECT AServ.id FROM public.\"AdditionalServices\" AServ, public.\"Food\" F WHERE F.id=\"idFood\" AND F.type='" + comboBox3.Text + "'");
                int ind3 = Int32.Parse(str);
                SQLquery(String.Format("SELECT public.\"InsertRecreationBase\"({0}, {1}, '{2}', '{3}', {4})",
                    ind1, ind2, dateTimePicker1.Value, dateTimePicker2.Value, ind3)
                );
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля"); }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (comboBox1.Text != "" && comboBox2.Text != "" && comboBox3.Text != "" && TabSTR == "dataGridView1")
            {
                string str = SQLquerySTR("SELECT id FROM public.\"Client\" WHERE surname='" + comboBox1.Text + "'");
                int ind1 = Int32.Parse(str);
                str = SQLquerySTR("SELECT id FROM public.\"RoomNumber\" WHERE \"roomNumber\"='" + comboBox2.Text + "'");
                int ind2 = Int32.Parse(str);
                str = SQLquerySTR("SELECT AServ.id FROM public.\"AdditionalServices\" AServ, public.\"Food\" F WHERE F.id=\"idFood\" AND F.type='" + comboBox3.Text + "'");
                int ind3 = Int32.Parse(str);
                SQLquery(String.Format("SELECT public.\"UpdateRecreationBase\"({0}, {1}, '{2}', '{3}', {4}, {5})",
                    ind1, ind2 , dateTimePicker1.Value, dateTimePicker2.Value, ind3, TableIndex)
                );
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля та виберіть рядок"); }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (comboBox1.Text != "" && comboBox2.Text != "" && comboBox3.Text != "" && TabSTR == "dataGridView1")
            {
                SQLquery(String.Format("SELECT public.\"DeleteRecreationBase\"({0})", TableIndex));
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Виберіть рядок"); }
        }

        private void dataGridView1_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                TableIndex = Int32.Parse(dataGridView1.Rows[e.RowIndex].Cells["ID"].Value.ToString());
                TabSTR = "dataGridView1";
                comboBox1.Text = dataGridView1.Rows[e.RowIndex].Cells["surname"].Value.ToString();
                comboBox2.Text = dataGridView1.Rows[e.RowIndex].Cells["idRoomNumber"].Value.ToString();
                comboBox3.Text = dataGridView1.Rows[e.RowIndex].Cells["idAdditionalServicesFOOD"].Value.ToString();
                dateTimePicker1.Value = DateTime.Parse(dataGridView1.Rows[e.RowIndex].Cells["startDate"].Value.ToString());
                dateTimePicker1.Value = DateTime.Parse(dataGridView1.Rows[e.RowIndex].Cells["endDate"].Value.ToString());
            }
            catch (Exception E) { MessageBox.Show("Порожній рядок"); }
        }

        //Client
        private void button4_Click(object sender, EventArgs e)
        {
            if (textBox1.Text != "" && textBox2.Text != "" && textBox3.Text != "" && textBox4.Text != "")
            {
                SQLquery(String.Format("SELECT public.\"InsertClient\"('{0}', '{1}', '{2}', '{3}')",
                    textBox1.Text, textBox2.Text, textBox3.Text, textBox4.Text)
                );
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля"); }
        }

        private void button5_Click(object sender, EventArgs e)
        {
            if (textBox1.Text != "" && textBox2.Text != "" && textBox3.Text != "" && textBox4.Text != "" && TabSTR == "dataGridView2")
            {
                SQLquery(String.Format("SELECT public.\"UpdateClient\"('{0}', '{1}', '{2}', '{3}', {4})",
                    textBox1.Text, textBox2.Text, textBox3.Text, textBox4.Text, TableIndex)
                );
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля та виберіть рядок"); }
        }

        private void button6_Click(object sender, EventArgs e)
        {
            if (textBox1.Text != "" && textBox2.Text != "" && textBox3.Text != "" && textBox4.Text != "" && TabSTR == "dataGridView2")
            {
                SQLquery(String.Format("SELECT public.\"DeleteClient\"({0})", TableIndex));
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Виберіть рядок"); }
        }

        private void dataGridView2_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                TableIndex = Int32.Parse(dataGridView2.Rows[e.RowIndex].Cells["ID"].Value.ToString());
                TabSTR = "dataGridView2";
                textBox1.Text = dataGridView2.Rows[e.RowIndex].Cells["surname"].Value.ToString();
                textBox2.Text = dataGridView2.Rows[e.RowIndex].Cells["name"].Value.ToString();
                textBox3.Text = dataGridView2.Rows[e.RowIndex].Cells["patronymic"].Value.ToString();
                textBox4.Text = dataGridView2.Rows[e.RowIndex].Cells["phone"].Value.ToString();
            }
            catch (Exception E) { MessageBox.Show("Порожній рядок"); }
        }

        //RoomNumber
        private void button7_Click(object sender, EventArgs e)
        {
            if (textBox5.Text != "" && textBox6.Text != "" && textBox7.Text != "" && comboBox4.Text != "" && comboBox5.Text != "")
            {
                string str = SQLquerySTR("SELECT id FROM public.\"Comfort\" WHERE type = '" + comboBox4.Text + "'");
                int ind1 = Int32.Parse(str);
                str = SQLquerySTR("SELECT id FROM public.\"RoomStatus\" WHERE status = '" + comboBox5.Text + "'");
                int ind2 = Int32.Parse(str);
                SQLquery(String.Format("SELECT public.\"InsertRoomNumber\"('{0}', {1}, '{2}', {3}, {4})",
                    textBox5.Text, textBox6.Text, textBox7.Text, ind1, ind2)
                );
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля"); }
        }

        private void button8_Click(object sender, EventArgs e)
        {
            if (textBox5.Text != "" && textBox6.Text != "" && textBox7.Text != "" && comboBox4.Text != "" && comboBox5.Text != "" && TabSTR == "dataGridView3")
            {
                string str = SQLquerySTR("SELECT id FROM public.\"Comfort\" WHERE type = '" + comboBox4.Text + "'");
                int ind1 = Int32.Parse(str);
                str = SQLquerySTR("SELECT id FROM public.\"RoomStatus\" WHERE status = '" + comboBox5.Text + "'");
                int ind2 = Int32.Parse(str);
                SQLquery(String.Format("SELECT public.\"UpdateRoomNumber\"({0}, {1}, '{2}', '{3}', {4}, {5})",
                    textBox5.Text, textBox6.Text, textBox7.Text, ind1, ind2, TableIndex)
                );
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля та виберіть рядок"); }
        }

        private void button9_Click(object sender, EventArgs e)
        {
            if (textBox5.Text != "" && textBox6.Text != "" && textBox7.Text != "" && comboBox4.Text != "" && comboBox5.Text != "" && TabSTR == "dataGridView3")
            {
                SQLquery(String.Format("SELECT public.\"DeleteRoomNumber\"({0})", TableIndex));
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Виберіть рядок"); }
        }

        private void dataGridView3_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                TableIndex = Int32.Parse(dataGridView3.Rows[e.RowIndex].Cells["id"].Value.ToString());
                TabSTR = "dataGridView3";
                textBox5.Text = dataGridView3.Rows[e.RowIndex].Cells["roomNumber"].Value.ToString();
                textBox6.Text = dataGridView3.Rows[e.RowIndex].Cells["numberOfSeats"].Value.ToString();
                textBox7.Text = dataGridView3.Rows[e.RowIndex].Cells["price"].Value.ToString();
                comboBox4.Text = dataGridView3.Rows[e.RowIndex].Cells["idComfort"].Value.ToString();
                comboBox5.Text = dataGridView3.Rows[e.RowIndex].Cells["idRoomStatus"].Value.ToString();
            }
            catch (Exception E) { MessageBox.Show("Порожній рядок"); }
        }

        //Comfort
        private void button10_Click(object sender, EventArgs e)
        {
            if (textBox8.Text != "")
            {
                SQLquery(String.Format("SELECT public.\"InsertComfort\"('{0}')", textBox8.Text));
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля"); }
        }

        private void button11_Click(object sender, EventArgs e)
        {
            if (textBox8.Text != "" && TabSTR == "dataGridView4")
            {
                SQLquery(String.Format("SELECT public.\"UpdateComfort\"('{0}', {1})",
                    textBox8.Text, TableIndex)
                );
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля та виберіть рядок"); }
        }

        private void button12_Click(object sender, EventArgs e)
        {
            if (textBox8.Text != "" && TabSTR == "dataGridView4")
            {
                SQLquery(String.Format("SELECT public.\"DeleteComfort\"({0})", TableIndex));
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Виберіть рядок"); }
        }

        private void dataGridView4_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                TableIndex = Int32.Parse(dataGridView4.Rows[e.RowIndex].Cells["id"].Value.ToString());
                TabSTR = "dataGridView4";
                textBox8.Text = dataGridView4.Rows[e.RowIndex].Cells["type"].Value.ToString();
            }
            catch (Exception E) { MessageBox.Show("Порожній рядок"); }
        }

        //RoomStatus
        private void button13_Click(object sender, EventArgs e)
        {
            if (textBox9.Text != "")
            {
                SQLquery(String.Format("SELECT public.\"InsertRoomStatus\"('{0}')", textBox9.Text));
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля"); }
        }

        private void button14_Click(object sender, EventArgs e)
        {
            if (textBox9.Text != "" && TabSTR == "dataGridView5")
            {
                SQLquery(String.Format("SELECT public.\"UpdateRoomStatus\"('{0}', {1})",
                    textBox9.Text, TableIndex)
                );
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля та виберіть рядок"); }
        }

        private void button15_Click(object sender, EventArgs e)
        {
            if (textBox9.Text != "" && TabSTR == "dataGridView5")
            {
                SQLquery(String.Format("SELECT public.\"DeleteRoomStatus\"({0})", TableIndex));
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Виберіть рядок"); }
        }

        private void dataGridView5_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                TableIndex = Int32.Parse(dataGridView5.Rows[e.RowIndex].Cells["id"].Value.ToString());
                TabSTR = "dataGridView5";
                textBox9.Text = dataGridView5.Rows[e.RowIndex].Cells["type"].Value.ToString();
            }
            catch (Exception E) { MessageBox.Show("Порожній рядок"); }
        }

        //AdditionalServices
        private void button16_Click(object sender, EventArgs e)
        {
            if (comboBox6.Text != "" && comboBox7.Text != "")
            {
                string str = SQLquerySTR("SELECT id FROM public.\"Food\" WHERE type = '" + comboBox6.Text + "'");
                int ind1 = Int32.Parse(str);
                str = SQLquerySTR("SELECT id FROM public.\"SpaTreatments\" WHERE \"typeSt\" =  '" + comboBox7.Text + "'");
                int ind2 = Int32.Parse(str);

                SQLquery(String.Format("SELECT public.\"InsertAdditionalServices\"({0}, {1})",
                    ind1, ind2)
                );
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля"); }
        }

        private void button18_Click(object sender, EventArgs e)
        {
            if (comboBox6.Text != "" && comboBox7.Text != "" && TabSTR == "dataGridView6")
            {
                string str = SQLquerySTR("SELECT id FROM public.\"Food\" WHERE type = '" + comboBox6.Text + "'");
                int ind1 = Int32.Parse(str);
                str = SQLquerySTR("SELECT id FROM public.\"SpaTreatments\" WHERE \"typeSt\" =  '" + comboBox7.Text + "'");
                int ind2 = Int32.Parse(str);
                SQLquery(String.Format("SELECT public.\"UpdateAdditionalServices\"({0}, {1}, {2})",
                    ind1, ind2, TableIndex)
                );
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля та виберіть рядок"); }
        }

        private void button17_Click(object sender, EventArgs e)
        {
            if (comboBox6.Text != "" && comboBox7.Text != "" && TabSTR == "dataGridView6")
            {
                SQLquery(String.Format("SELECT public.\"DeleteAdditionalServices\"({0})", TableIndex));
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Виберіть рядок"); }
        }

        private void dataGridView6_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                TableIndex = Int32.Parse(dataGridView6.Rows[e.RowIndex].Cells["id"].Value.ToString());
                TabSTR = "dataGridView6";
                comboBox6.Text = dataGridView6.Rows[e.RowIndex].Cells["idFood"].Value.ToString();
                comboBox7.Text = dataGridView6.Rows[e.RowIndex].Cells["idSpaTreatments"].Value.ToString();
            }
            catch (Exception E) { MessageBox.Show("Порожній рядок"); }
        }

        //Food
        private void button19_Click(object sender, EventArgs e)
        {
            if (textBox10.Text != "")
            {
                SQLquery(String.Format("SELECT public.\"InsertFood\"('{0}')", textBox10.Text));
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля"); }
        }

        private void button20_Click(object sender, EventArgs e)
        {
            if (textBox10.Text != "" && TabSTR == "dataGridView7")
            {
                SQLquery(String.Format("SELECT public.\"UpdateFood\"('{0}', {1})",
                    textBox10.Text, TableIndex)
                );
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля та виберіть рядок"); }
        }

        private void button21_Click(object sender, EventArgs e)
        {
            if (textBox10.Text != "" && TabSTR == "dataGridView7")
            {
                SQLquery(String.Format("SELECT public.\"DeleteFood\"({0})", TableIndex));
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Виберіть рядок"); }
        }

        private void dataGridView7_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                TableIndex = Int32.Parse(dataGridView7.Rows[e.RowIndex].Cells["id"].Value.ToString());
                TabSTR = "dataGridView7";
                textBox10.Text = dataGridView7.Rows[e.RowIndex].Cells["type"].Value.ToString();
            }
            catch (Exception E) { MessageBox.Show("Порожній рядок"); }
        }

        //SpaTreatments
        private void button22_Click(object sender, EventArgs e)
        {
            if (textBox11.Text != "")
            {
                SQLquery(String.Format("SELECT public.\"InsertSpaTreatments\"('{0}')", textBox11.Text));
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля"); }
        }

        private void button23_Click(object sender, EventArgs e)
        {
            if (textBox11.Text != "" && TabSTR == "dataGridView8")
            {
                SQLquery(String.Format("SELECT public.\"UpdateSpaTreatments\"('{0}', {1})",
                    textBox11.Text, TableIndex)
                );
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Заповніть порожні поля та виберіть рядок"); }
        }

        private void button24_Click(object sender, EventArgs e)
        {
            if (textBox11.Text != "" && TabSTR == "dataGridView8")
            {
                SQLquery(String.Format("SELECT public.\"DeleteSpaTreatments\"({0})", TableIndex));
                TabSTR = "";
                UpdateINFO();
            }
            else { MessageBox.Show("Виберіть рядок"); }
        }

        private void dataGridView8_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                TableIndex = Int32.Parse(dataGridView8.Rows[e.RowIndex].Cells["id"].Value.ToString());
                TabSTR = "dataGridView8";
                textBox11.Text = dataGridView8.Rows[e.RowIndex].Cells["type"].Value.ToString();
            }
            catch (Exception E) { MessageBox.Show("Порожній рядок"); }
        }
    }
}
