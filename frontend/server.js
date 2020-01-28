//Importa as dependências que acabamos de instalar
const express = require('express');
const path = require('path');
const nomeApp = process.env.npm_package_name;
const port = process.env.PORT || 8081

const app = express();

// Serve os arquivos estáticos da pasta dist (gerada pelo ng build)
app.use(express.static(`${__dirname}/dist/${nomeApp}`));

app.get('/*', function(req,res) {
    
res.sendFile(path.join(`${__dirname}/dist/${nomeApp}/index.html`'));
});

// Inicia a aplicação pela porta configurada
app.listen(port);