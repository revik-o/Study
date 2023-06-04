pwd
export LD_LIBRARY_PATH=$PWD
echo $LD_LIBRARY_PATH
RUSTFLAGS='-C target-feature=+crt-static' cargo build --release