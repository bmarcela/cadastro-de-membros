package com.projeto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.projeto.modelagemDeDados.Dados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GUIController {

    @FXML
    private TextField nomeField;
    @FXML
    private DatePicker dataNascPicker;
    @FXML
    private TextField enderecoField;
    @FXML
    private TextField telefoneField;
    @FXML
    private CheckBox adore;
    @FXML
    private CheckBox ga;
    @FXML
    private CheckBox deeper;
    @FXML
    private CheckBox ebd;
    @FXML
    private CheckBox pgm;
    @FXML
    private CheckBox celebrando;
    @FXML 
    private CheckBox tdc;
    @FXML
    private CheckBox inove;
    @FXML
    private CheckBox missoes;
    @FXML
    private CheckBox ide;
    @FXML
    private CheckBox casais;
    @FXML
    private CheckBox master;
    @FXML
    private Button inserir;
    @FXML
    private Button atualizar;
    @FXML
    private TextField nomeDelete;
    @FXML
    private Button delete;

    @FXML
    private TableView<Dados> tableView;
    @FXML
    private TableColumn<Dados, Integer> codColumn;
    @FXML
    private TableColumn<Dados, String> nomeColumn;
    @FXML
    private TableColumn<Dados, Date> dataNascColumn;
    @FXML
    private TableColumn<Dados, String> telefoneColumn;
    @FXML
    private TableColumn<Dados, String> enderecoColumn;
    @FXML
    private TableColumn<Dados, String> ministerioColumn;
    @FXML
    private ObservableList<Dados> CadastroHomens;

    // Carregando os dados
    public void loadRecords() throws SQLException {
        try (Connection conexao = ConexaoDB.getConexao()) {
            String sql = "SELECT * FROM homens";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
        } catch (SQLException e) {
            showAlert("Erro", "Não foi possível carregar os dados: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Inserindo novos membros:
    @FXML
    private void insertRecords() throws SQLException {
        List<String> ministerioSelecionado = new ArrayList<>();
        if (adore.isSelected()) ministerioSelecionado.add("Ministério Adore");
        if (ga.isSelected()) ministerioSelecionado.add("Geração Amarelinha");
        if (deeper.isSelected()) ministerioSelecionado.add("Deeper");
        if (ebd.isSelected()) ministerioSelecionado.add("Escola Bíblica Dominical");
        if (pgm.isSelected()) ministerioSelecionado.add("Pequenos Grupos Multiplicadores");
        if (celebrando.isSelected()) ministerioSelecionado.add("Celebrando a Vida");
        if (tdc.isSelected()) ministerioSelecionado.add("Time de Cristo");
        if (inove.isSelected()) ministerioSelecionado.add("Inove");
        if (missoes.isSelected()) ministerioSelecionado.add("Missões");
        if (ide.isSelected()) ministerioSelecionado.add("IDE");
        if (casais.isSelected()) ministerioSelecionado.add("Ministério de Casais");
        if (master.isSelected()) ministerioSelecionado.add("Master");

        String ministerio = String.join(",", ministerioSelecionado);
        
        String nome = nomeField.getText();
        LocalDate dataNasc = dataNascPicker.getValue();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();

        if (nome.isEmpty() || dataNasc == null || endereco.isEmpty() || telefone.isEmpty()) {
            showAlert("Erro", "Todos os campos devem ser preenchidos", Alert.AlertType.WARNING);
        }

        try (Connection conexao = ConexaoDB.getConexao()) {
            String sql = "INSERT INTO homens (nome, dataNasc, endereco, telefone, ministerio) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setDate(2, Date.valueOf(dataNasc));
            stmt.setString(3, endereco);
            stmt.setString(4, telefone);
            stmt.setString(5, ministerio);
            stmt.executeUpdate();
            loadRecords();
            showAlert("Sucesso", "Dados inseridos com sucesso!", Alert.AlertType.CONFIRMATION);
            nomeField.clear();
            dataNascPicker.getEditor().clear();
            enderecoField.clear();
            telefoneField.clear();
                        
        } catch (SQLException e) {
            showAlert("Erro", "Não foi possível inserir o registro: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Deletando registros:
    @FXML
    private void deleteRecords() throws SQLException {
        String nome = nomeDelete.getText();

        if (nome.isEmpty()) {
            showAlert("Erro", "Para deletar um registro, é necessário que seja fornecido o nome completo.", Alert.AlertType.ERROR);
        }

        try (Connection conexao = ConexaoDB.getConexao()) {
            String sql = "DELETE FROM homens WHERE nome = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.executeUpdate();

            showAlert("Sucesso", "Registro deletado.", Alert.AlertType.CONFIRMATION);
            nomeDelete.clear();
        } catch (SQLException e) {
            showAlert("Erro", "Não foi possível deletar o registro: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    
    }

    public void initialize() {
        new ConexaoDB();
        CadastroHomens = FXCollections.observableArrayList();
        codColumn.setCellValueFactory(new PropertyValueFactory<>("cod")); 
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        dataNascColumn.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        enderecoColumn.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        ministerioColumn.setCellValueFactory(new PropertyValueFactory<>("ministerio"));

        carregar();
        tableView.setItems(CadastroHomens);
    }

    @FXML
    private void carregar() {
        try (Connection conexao = ConexaoDB.getConexao()) {
            // System.out.println("Conexão efetuada.");
            String sql = "SELECT * FROM homens";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            // System.out.println("ResultSet funcionando.");
            
            while(rs.next()) {
                CadastroHomens.add(new Dados(rs.getInt("cod"), rs.getString("nome"), rs.getDate("dataNasc"),
                 rs.getString("endereco"), rs.getString("telefone"), rs.getString("ministerio")));
            }
            tableView.setItems(CadastroHomens);
        } catch (SQLException e) {
            System.out.println("Não foi possível executar a ação." + e.getMessage());;
        }
    }

    public void addRecords(@SuppressWarnings("exports") Dados dados) {
        CadastroHomens.add(dados);
    }


    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alerta = new Alert(alertType);
        alerta.setTitle(title);
        alerta.setHeaderText(null);
        alerta.setContentText(message);
        alerta.showAndWait();
    }
}
