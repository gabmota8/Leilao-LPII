# Use a imagem oficial do MySQL
FROM mysql:8.0

# Expor a porta padrão do MySQL
EXPOSE 3306

# Definir variáveis de ambiente
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=meubanco
ENV MYSQL_USER=usuario
ENV MYSQL_PASSWORD=senha

# Iniciar o servidor MySQL
CMD ["mysqld"]
