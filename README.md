Run the application on port 8099:

`mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8099`

Or to run it on the default port that's set in `application.properties`:

`mvn spring-boot:run`

Build a Docker image:

`docker build -t ephraimglick/ten-percent .`

Run the dockerized application at port 8099:

`docker run -p 8099:8080 ephraimglick/ten-percent`

Connect to the EC2 instance:

- Make sure your security group allows inbound traffic on port 22 from your IP address
- SSH in using user name and IP: `ssh -i /path/my-key-pair.pem my-instance-user-name@my-instance-public-dns-name`. The default user name is `ec2-user`. In our case the command might be, e.g.: `ssh -i ten-percent-key.pem ec2-user@3.101.112.96`

Run your application on your EC2 instance:

- SSH into your instance
- Install Docker: https://docs.aws.amazon.com/AmazonECS/latest/developerguide/docker-basics.html
- Pull Docker image: `docker pull ephraimglick/ten-percent:latest`
- Run `docker images` to find the image, and start it with, e.g., `docker start -p 80:8080 2991ab8bf038`. The port mapping should work with your EC2 security rules.