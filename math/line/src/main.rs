const LUMINANCE_LEVELS: &str = ".,-~:;=!*#$@";
const CIRCLE_RADIUS: i32 = 3;
const DONUT_RADIUS: i32 = 2;

fn clear_screen() {
    print!("\x1B[H")
}

fn debug_pause(millis: u64) {
    std::thread::sleep(std::time::Duration::from_millis(millis))
}

fn main() {
    const width: usize = 125;
    const height: usize = 45;
    const point_density: f32 = 0.1;

    loop {
        let mut draw_calls: i32 = 0;
        let mut circle_x_angle: f32 = 0.1;
        let mut circle_y_angle: f32 = 0.1;
        let mut donut_x_angle: f32 = 0.1;
        let mut donut_y_angle: f32 = 0.1;
        let end_poing: f32 = 1.0;

        // while circle_x_angle < end_poing {
        let mut frame = [[' '; height]; width];

            circle_x_angle += point_density;
            draw_calls += 1;

            while circle_y_angle < end_poing {
                circle_y_angle += point_density;
                draw_calls += 1;
                let circle_x_point = circle_x_angle.cos();
                let circle_y_point = circle_y_angle.sin();
                let circle_x_offset = circle_x_angle.cos();
                let circle_y_offset = circle_y_angle.sin();
                let final_circle_x_coordinate =
                    draw_calls as f32 * circle_y_angle.cos() + circle_y_angle.sin();
                let final_circle_y_coordinate =
                    draw_calls as f32 * - circle_y_angle.sin() + circle_y_angle.cos();
                // let final_circle_z_coordinate = ((DONUT_RADIUS as f32
                //     + CIRCLE_RADIUS as f32 * circle_x_angle)
                //     * circle_y_offset);

                let frame_coordinate_x: usize = final_circle_x_coordinate as usize;
                let frame_coordinate_y: usize = (final_circle_y_coordinate * -1.0) as usize;

                if frame_coordinate_y < height && frame_coordinate_x < width {
                    frame[frame_coordinate_x][frame_coordinate_y] =
                        LUMINANCE_LEVELS.as_bytes()[0] as char;
                }
            }

            clear_screen();
            println!("draw calls: {}", draw_calls);
            for frame_line in frame {
                for frame_pixel in frame_line {
                    print!("{}", frame_pixel);
                }
            }
            debug_pause(1000);
        // }
    }
}
