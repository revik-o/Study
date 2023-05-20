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

pub fn performance_test() -> u32 {
    print!(" Performance test... ");
    let mut operation_index: u32 = 0;
    let start_time = std::time::Instant::now();

    loop {
        operation_index += 1;
        let mut test_array = [' '; 80 * 20];

        for array_index in 0..test_array.len() {
            test_array[array_index] = '-';
            print!("{}", test_array[array_index]);
        }
        clean_screen();

        if std::time::Instant::now()
            .saturating_duration_since(start_time)
            .as_secs_f32()
            > 0.99999
        {
            print!("{}", operation_index);
            return operation_index;
        }
    }
}
