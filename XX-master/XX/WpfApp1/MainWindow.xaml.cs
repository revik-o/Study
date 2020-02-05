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

namespace WpfApp1
{
    /// <summary>
    /// Логика взаимодействия для MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            CB.Items.Add("Дискриминант");
            CB.Items.Add("Виета");

            CB.SelectedIndex = 0;
        }

        //To Count
        private void B1_Click(object sender, RoutedEventArgs e)
        {
            double a = 0, b = 0, c = 0, D, X1 = 0, X2 = 0;

            try
            {
                a = Convert.ToDouble(Tb1.Text);

                b = Convert.ToDouble(Tb2.Text);

                if (Tb3.Text == "" || Tb3.Text == " " || Tb3.Text == "\0") Tb3.Text = "0";
                else c = Convert.ToDouble(Tb3.Text);

                if(CB.SelectedIndex == 0)
                {
                    D = Math.Pow(b, 2) - (4 * a * c);
                    if (D > 0){
                        X1 = (-b + Math.Sqrt(D)) / (2 * a);
                        X2 = (-b - Math.Sqrt(D)) / (2 * a);
                        double y, x;
                        x = a * Math.Pow(X1, 2) + b * X1 + c;
                        y = a * Math.Pow(X2, 2) + b * X2 + c;
                        if (x == 0 && x == y)
                        {
                            L1.Content = "X1 = " + X1 + "\nX2 = " + X2;
                        }
                        else L1.Content = "В примере проблема";
                    }
                    else if (D == 0){
                        X1 = (-b) / (2 * a);
                        L1.Content = "X = " + X1;
                    }else if (D < 0) L1.Content = "Корней нет";
                }
                else if(CB.SelectedIndex == 1){
                    //MessageBox.Show("Vieta");
                    for (double o = -(Math.Abs(a) + Math.Abs(b) + Math.Abs(c)); o <= Math.Abs(a) + Math.Abs(b) + Math.Abs(c); o++)
                    {
                        //MessageBox.Show("Vieta");
                        for (double l = o; l <= Math.Abs(a) + Math.Abs(b) + Math.Abs(c); l++)
                        {
                            //MessageBox.Show("Vieta");
                            if ((o + l == b) && (o * l == c))
                            {
                                X1 = o*-1;
                                X2 = l*-1;
                                double y, x;
                                x = a * Math.Pow(X1, 2) + b * X1 + c;
                                y = a * Math.Pow(X2, 2) + b * X2 + c;
                                if (x == 0 && x == y)
                                {
                                    L1.Content = "X1 = " + X1 + "\nX2 = " + X2;
                                }
                                else L1.Content = "В примере проблема";
                                break;
                            }
                        }
                    }
                    if (X1 == 0 && X2 == 0) MessageBox.Show("zero");
                }

            }
            catch
            {
                L1.Content = "В примере проблема";
            }
        }

        private void CleaR_Click(object sender, RoutedEventArgs e)
        {
            Tb1.Text = "";
            Tb2.Text = "";
            Tb3.Text = "";
            L1.Content = "";
        }

        private void Close_Click(object sender, RoutedEventArgs e)
        {
            Environment.Exit(0);
        }
    }
}
