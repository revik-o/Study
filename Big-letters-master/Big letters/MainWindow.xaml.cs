using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Big_letters
{
    /// <summary>
    /// Логика взаимодействия для MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        //string str;
        //int P;

        public MainWindow()
        {
            InitializeComponent();

            //P = 0;

            /*for (int i = 0; i < TB1.Text.Length; i++)
            {
                str += char.ToUpper(TB1.Text[i]);
            }*/
        }

        void Key_cl(object sender, KeyEventArgs e)
        {
            if(e.Key == Key.Enter)
            {
                COPY___();
            }
        }

        void F_R()
        {
            TB2.Text = "";
            for (int i = 0; i < TB1.Text.Length; i++)
            {
                TB2.Text += char.ToUpper(TB1.Text[i]);
            }
        }

        void COPY___()
        {
            MessageBox.Show("Copied to clipboard");
            F_R();
            Clipboard.SetText(TB2.Text);
        }

        void Check_leter(object sender, TextCompositionEventArgs e)
        {
            TB2.Text = "";
            F_R();
        }

        private void But_Copy_Click(object sender, RoutedEventArgs e)
        {
            COPY___();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            TB1.Text = "";
            TB2.Text = "";
        }

        private void Update_Click(object sender, RoutedEventArgs e)
        {
            F_R();
        }
    }
}
