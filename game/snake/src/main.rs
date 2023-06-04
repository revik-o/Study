// #[link(name = "rand32", kind = "static")]
extern {
    fn rand_int(range: i32) -> i32;
}

use types::Snake;
use utils::clean_screen;

mod types;
mod utils;

// think about it vec[x + width * y]

fn make_scene(frame: &mut Vec<Vec<char>>, resolution: &types::Resolution) {
    for y_coordinate in 0..resolution.height {
        if y_coordinate == 0 {
            for x_coordinate in 0..resolution.width {
                frame[y_coordinate as usize][x_coordinate as usize] = '#';
            }

            continue;
        }
        if y_coordinate == resolution.height - 1 {
            for x_coordinate in 0..resolution.width {
                frame[y_coordinate as usize][x_coordinate as usize] = '#';
            }

            break;
        }

        frame[y_coordinate as usize][0] = '#';
        frame[y_coordinate as usize][resolution.width as usize - 1] = '#';
    }
}

fn draw_frame(frame: &Vec<Vec<char>>) {
    clean_screen();

    for vec_row in frame {
        for symbol in vec_row {
            print!("{}", symbol);
        }
    }
}

fn main() {
    println!("hi");
    unsafe {
        rand_int(10)
    }
    unsafe { println!("{}", rand_int(10)) };
    let resolution = types::Resolution {
        height: 23, // chars
        width: 80,
    };
    let performance_result = utils::performance_test();
    let mut fps = 0;
    let mut frame = vec![vec![' '; resolution.width as usize]; resolution.height as usize];
    let mut snake = Snake::new(
        1,
        resolution.width - 1,
        1,
        resolution.height - 1,
        performance_result,
    );
    utils::test_the_screen(&resolution);
    let mut current_fps_time = std::time::Instant::now();

    loop {
        snake.snake_move();
        make_scene(&mut frame, &resolution);
        snake.render_snake_on_frame(&mut frame);
        draw_frame(&frame);
        frame = vec![vec![' '; resolution.width as usize]; resolution.height as usize];
        fps += 1;

        if std::time::Instant::now()
            .saturating_duration_since(current_fps_time)
            .as_secs_f32()
            > 0.99999
        {
            print!("fps: {}", fps);
            fps = 0;
            current_fps_time = std::time::Instant::now();
        }
    }
}
