sudo systemctl start docker.service
sudo docker run --rm --name mysql__ -h 127.0.0.1:127.0.0.1 -p 3306:3306 -v /home/oleg/git/AvadaMediaDZ/docker/data/:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=12345678 -d mysql 
sudo docker exec -it mysql__ bash
