RUSTFLAGS='-C target-feature=+crt-static,link-arg=-Wl,-rpath,/usr/bin' cargo build --release
