import java.util.ArrayList;
import java.util.List;

public class Historico {

    private List<TextoMemento> estados = new ArrayList<>();

    public void salvarEstado(EditorTexto editor) {
        estados.add(editor.salvar());
    }

    public void restaurarEstado(EditorTexto editor, int indice) {
        if (indice < 0) {
            throw new IllegalArgumentException("Índice inválido");
        }

        if (indice >= estados.size()) {
            throw new IllegalArgumentException("Estado inexistente");
        }

        editor.restaurar(estados.get(indice));
    }
}
