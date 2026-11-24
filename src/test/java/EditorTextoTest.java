import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorTextoTest {

    @Test
    void deveSalvarTexto() {
        EditorTexto editor = new EditorTexto();
        editor.setTexto("Primeiro texto");

        TextoMemento memento = editor.salvar();

        assertEquals("Primeiro texto", memento.getTexto());
    }

    @Test
    void deveRestaurarPrimeiroEstado() {
        EditorTexto editor = new EditorTexto();
        Historico historico = new Historico();

        editor.setTexto("Primeiro texto");
        historico.salvarEstado(editor);

        editor.setTexto("Segundo texto");
        historico.salvarEstado(editor);

        historico.restaurarEstado(editor, 0);

        assertEquals("Primeiro texto", editor.getTexto());
    }

    @Test
    void deveRestaurarSegundoEstado() {
        EditorTexto editor = new EditorTexto();
        Historico historico = new Historico();

        editor.setTexto("Primeiro texto");
        historico.salvarEstado(editor);

        editor.setTexto("Segundo texto");
        historico.salvarEstado(editor);

        editor.setTexto("Terceiro texto");

        historico.restaurarEstado(editor, 1);

        assertEquals("Segundo texto", editor.getTexto());
    }

    @Test
    void deveRestaurarTextoVazio() {
        EditorTexto editor = new EditorTexto();
        Historico historico = new Historico();

        historico.salvarEstado(editor);

        editor.setTexto("Novo texto");

        historico.restaurarEstado(editor, 0);

        assertEquals("", editor.getTexto());
    }

    @Test
    void deveRetornarExcecaoTextoNulo() {
        try {
            EditorTexto editor = new EditorTexto();
            editor.setTexto(null);

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Texto inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoIndiceNegativo() {
        try {
            EditorTexto editor = new EditorTexto();
            Historico historico = new Historico();

            historico.restaurarEstado(editor, -1);

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Índice inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoEstadoInexistente() {
        try {
            EditorTexto editor = new EditorTexto();
            Historico historico = new Historico();

            historico.restaurarEstado(editor, 0);

            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Estado inexistente", e.getMessage());
        }
    }
}