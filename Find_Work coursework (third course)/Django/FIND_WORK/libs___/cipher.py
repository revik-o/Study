def cipher(password):
    from FIND_WORK.settings import SECRET_KEY
    key = ''
    for byte in SECRET_KEY.encode():
        for byte_password in password.encode():
            key += chr(byte * byte_password)

    return key