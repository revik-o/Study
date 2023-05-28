mod util;
use rui::*;

fn main() {
    rui(vstack((
        hstack(children).padding(Auto),
        hstack(children).padding(Auto),
    )));
}
