#include <stdio.h>
#include <sys/select.h>
#include <termios.h>
#include <unistd.h>

int get_pressed_key()
{
    static const int STDIN = 0;
    static short initialized = 1;

    if (initialized)
    {
        // Use termios to turn off line buffering
        termios term;
        tcgetattr(STDIN, &term);
        term.c_lflag &= ~ICANON;
        tcsetattr(STDIN, TCSANOW, &term);
        setbuf(stdin, NULL);
        initialized = 0;
    }

    int bytesWaiting;
    ioctl(STDIN, FIONREAD, &bytesWaiting);
    return bytesWaiting;
}