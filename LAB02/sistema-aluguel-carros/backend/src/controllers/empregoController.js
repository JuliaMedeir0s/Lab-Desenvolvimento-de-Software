const emprego = require('../models/emprego');

exports.delete = (req, res, next) => {
    const id = req.params.id;
    emprego.findByPk(id).then(emprego =>{
        if(emprego){
            emprego.destroy();
            res.status(200).send(emprego);
        }else{
            res.status(404).send(`Emprego com id ${id} não encontrado.`);
        }
    }).catch(next);

}

exports.update = (req, res, next) => {
    const id = req.params.id;
    const newEmprego = req.body;
    emprego.findByPk(id).then(emprego => {
        if (!emprego) {
            res.status(404).send(`Emprego com id ${id} não encontrado.`);
        } else {
            emprego.update(newEmprego).then(() => {
                res.status(200).send(emprego);
            }).catch(err => {
                next(err);
            });
        }
    }).catch(err => {
        next(err);
    });
}
