use std::io;
use regex_lite::Regex;

use prototype-01::moves::MoveError;
use prototype-01::moves::enumerator::list_moves;
use prototype-01::types::{Board,Player,Move};
use prototype-01::validators::win_validator;

fn do_move<'a>(mut board: Board<'a>, next_move:)