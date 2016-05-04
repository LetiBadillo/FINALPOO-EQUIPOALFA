/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pooequipoalfat4;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author jesus_ignacio_159
 */
public class LimiteDeTexto extends PlainDocument {
    private int limite;
    
    public LimiteDeTexto(int i) {
    super();
    this.limite = i;
}
    
    public void insertString(int compensacion, String str, AttributeSet sttr) throws BadLocationException {
        if (str == null) {
            return;
        }
        if ((getLength() + str.length()) <= limite) {
            super.insertString(compensacion, str, sttr);
        }
    }    
}