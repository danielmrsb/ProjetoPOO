/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author daniel.mbarbosa1
 */
public interface Editavel {
    public boolean salvar(Object x);
    public boolean atualizar(Object x);
    public boolean excluir(Object x);
    public Object get(Object x);
    public Object getVarios();
}
