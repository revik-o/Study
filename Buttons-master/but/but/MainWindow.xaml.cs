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

namespace but
{
    /// <summary>
    /// Логика взаимодействия для MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            L();
            button.Click += (sender, args) =>
            {
                PP(button);
            };
        }

        int p; //счётчик кнопок

        Button button = new Button(); //выделение памяти под новую кнопку

        void L() //метод создания новой кнопки на пустой форме
        {
            if (p == 0)
            {
                button.Content = p + 1;
                button.Width = 50;
                button.Height = 50;
                GR.Children.Add(button);
                ++p;
            }
        }

        void PP(Button obj) //метод создания новой кнопки при нажатии на уже созданную кнопку
        {
            int o = p;

            Random rn = new Random();
            for (int i = 0; i < o; i++) { 
            obj = new Button();
                obj.Width = 50;
                obj.Height = 50;
                obj.Content = p + 1;
                int oop = rn.Next(0, 720);
                int ooop = rn.Next(0, 1280);
                Canvas.SetTop(obj, oop);
                Canvas.SetLeft(obj, ooop);
                cn.Children.Add(obj);
                obj.Click += (sender, args) =>
                {
                    PP(button);
                };
                ++p;

                if (p < 255) { //по приколу)
                    byte bby = 255;
                    bby -= Convert.ToByte(p);
                    GR.Background = new SolidColorBrush(Color.FromRgb(bby, bby, bby));
                }
            }
            o = p;
        }
    }
}
