#[cfg(test)]
mod tests {

    use chrono_english::{parse_date_string,Dialect};
    use chrono::prelude::*;

    #[test]
    fn chrono_parse_date() {
        let date_time = parse_date_string(&"December 31st".replace("st", ""), Local::now(), Dialect::Uk).unwrap();
        assert_eq!("Dec 31", format!("{}",date_time.format("%b %d")));
    }
}