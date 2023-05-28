pub fn get_system_language() -> &'static str {
    let lang = sys_locale::get_locale().unwrap_or_else(|| String::from("en"));

    if lang.contains("ua") {
        return "ua";
    }

    return "en";
}