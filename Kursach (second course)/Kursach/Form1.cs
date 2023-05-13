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
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            login.Text = "Guest";
            password.PasswordChar = '*';
            password.Text = "";
        }

        private void button1_Click(object sender, EventArgs e)
        {
            ENTER();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            login.Text = "";
            password.Text = "";
        }

        private void Form1_KeyDown(object sender, KeyEventArgs e)
        {
            if(e.KeyCode == Keys.Enter)
            {
                ENTER();
            }
        }

        private void ENTER()
        {
            switch (login.Text)
            {
                case "Admin":
                case "admin":
                    if (password.Text == "Admin" || password.Text == "admin")
                    {
                        Form2 form2 = new Form2();
                        form2.Show();
                        Hide();
                    }
                    else
                    {
                        MessageBox.Show("Невірний пароль");
                    }
                    break;
                case "Guest":
                case "guest":
                    //MessageBox.Show("Guest");
                    User user = new User();
                    user.Show();
                    Hide();
                    break;
                default:
                    MessageBox.Show("Введіть логін");
                    break;
            }
        }

        private void login_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                ENTER();
            }
        }

        private void password_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                ENTER();
            }
        }
    }
}
