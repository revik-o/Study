use std::mem::Discriminant;

use crate::utils;

pub struct Resolution {
    pub height: u32, // pixel == char
    pub width: u32,
}

impl Resolution {
    pub fn size(&self) -> usize {
        (self.height * self.width) as usize
    }
}

pub enum Way {
    UP,
    DOWN,
    LEFT,
    RIGHT,
}

impl Way {
    pub fn custom_selection_generate(ways: Vec<&Way>) -> &Way {
        return ways[utils::rand_range(0..ways.len())];
    }

    pub fn generate() -> &'static Way {
        return Way::custom_selection_generate(vec![&Way::UP, &Way::DOWN, &Way::LEFT, &Way::RIGHT]);
    }
}

pub struct SnakeBodyPard {
    pub symbol: char,
    pub x_coordinate: u32,
    pub y_coordinate: u32,
}

impl SnakeBodyPard {
    pub fn generate(
        x_min_limit: u32,
        x_max_limit: u32,
        y_min_limit: u32,
        y_max_limit: u32,
        symbol: char,
    ) -> SnakeBodyPard {
        SnakeBodyPard {
            x_coordinate: utils::rand_range(x_min_limit..x_max_limit),
            y_coordinate: utils::rand_range(y_min_limit..y_max_limit),
            symbol,
        }
    }
}

pub struct Snake {
    pub body_parts: Vec<SnakeBodyPard>,
    pub way: Discriminant<Way>,
    pub move_offcet: i32,
    pub move_index: i32,
}

impl Snake {
    pub fn change_way(&mut self, way: Way) {
        self.way = std::mem::discriminant(&way)
    }

    pub fn new(
        x_min_limit: u32,
        x_max_limit: u32,
        y_min_limit: u32,
        y_max_limit: u32,
        performance_result: u32,
    ) -> Snake {
        let snake_way = Way::custom_selection_generate(vec![&Way::UP, &Way::DOWN, &Way::LEFT]);
        let snake_head =
            SnakeBodyPard::generate(x_min_limit, x_max_limit, y_min_limit, y_max_limit, '@');
        let mut body_parts = vec![snake_head];

        for index in 0..2 {
            body_parts.push(SnakeBodyPard {
                symbol: '-',
                x_coordinate: body_parts[0].x_coordinate + index + 1,
                y_coordinate: body_parts[0].y_coordinate,
            })
        }

        Snake {
            body_parts: body_parts,
            way: std::mem::discriminant(snake_way),
            move_offcet: performance_result as i32 / 2,
            move_index: 0,
        }
    }

    pub fn render_snake_on_frame(&self, frame: &mut Vec<Vec<char>>) {
        for body_part in &self.body_parts {
            frame[body_part.y_coordinate as usize][body_part.x_coordinate as usize] =
                body_part.symbol
        }
    }

    pub fn snake_move(&mut self) {
        let head_x_coordinate = self.body_parts[0].x_coordinate;
        let head_y_coordinate = self.body_parts[0].y_coordinate;
        self.move_index += 1;

        if self.move_index >= self.move_offcet {
            let mut temp_x = self.body_parts[0].x_coordinate;
            let mut temp_y = self.body_parts[0].y_coordinate;

            for index in 1..self.body_parts.len() {
                let x = self.body_parts[index].x_coordinate;
                let y = self.body_parts[index].y_coordinate;
                self.body_parts[index] = SnakeBodyPard {
                    symbol: '-',
                    x_coordinate: temp_x,
                    y_coordinate: temp_y,
                };
                temp_x = x;
                temp_y = y;
            }

            self.body_parts[0] = SnakeBodyPard {
                symbol: '@',
                x_coordinate: (head_x_coordinate as i32
                    + if self.way == std::mem::discriminant(&Way::RIGHT) {
                        1
                    } else if self.way == std::mem::discriminant(&Way::LEFT) {
                        -1
                    } else {
                        0
                    }) as u32,
                y_coordinate: (head_y_coordinate as i32
                    + if self.way == std::mem::discriminant(&Way::DOWN) {
                        1
                    } else if self.way == std::mem::discriminant(&Way::UP) {
                        -1
                    } else {
                        0
                    }) as u32,
            };
            self.move_index = 0
        }
    }
}
