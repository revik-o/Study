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
    let resolution = types::Resolution {
        height: 23, // chars
        width: 80,
    };
    let mut frame = vec![vec![' '; resolution.width as usize]; resolution.height as usize];
    let mut snake = Snake::new(1, resolution.width - 1, 1, resolution.height - 1);
    utils::test_the_screen(&resolution);

    loop {
        snake.snake_move();
        make_scene(&mut frame, &resolution);
        snake.render_snake_on_frame(&mut frame);
        draw_frame(&frame);
        frame = vec![vec![' '; resolution.width as usize]; resolution.height as usize]
    }
}
