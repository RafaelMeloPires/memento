public class EditorTexto {

    private String texto = "";

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("Texto inválido");
        }

        this.texto = texto;
    }

    public TextoMemento salvar() {
        return new TextoMemento(texto);
    }

    public void restaurar(TextoMemento memento) {
        this.texto = memento.getTexto();
    }
}