use crate::types::Resolution;

pub fn debug_pause(millis: u64) {
    std::thread::sleep(std::time::Duration::from_millis(millis))
}

pub fn clean_screen() {
    print!("\x1B[H")
}

pub fn test_the_screen(resolution: &Resolution) {
    print!("Debug: test the screen");
    clean_screen();
    let screen_size = resolution.size();

    for _ in 0..screen_size {
        print!("#");
    }

    print!("{}x{}", resolution.width, resolution.height);
    debug_pause(1000);
    clean_screen();
}