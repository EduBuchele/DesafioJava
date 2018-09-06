package br.com.desafio.bean;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.desafio.dao.DAO;
import br.com.desafio.model.Tarefa;

@ManagedBean
@ViewScoped
public class TarefaBean {

	Tarefa tarefa = new Tarefa();

	DAO<Tarefa> dao = new DAO<Tarefa>(Tarefa.class);

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void gravarTarefa() {
		System.out.println("Gravando Tarefa: " + tarefa.getNomeTarefa());

		if (this.tarefa.getId() == null) {
			dao.adiciona(this.tarefa);
			this.tarefas = dao.listaTodos();
		} else {
			dao.atualiza(this.tarefa);
		}

		this.tarefa = new Tarefa();
	}

	public void remover(Tarefa tarefa) {
		dao.remove(tarefa);
		this.tarefas = dao.listaTodos();
	}

	public void editar(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	List<Tarefa> tarefas;

	public List<Tarefa> getTarefas() {
		if (this.tarefas == null) {
			this.tarefas = dao.listaTodos();
		}
		return tarefas;
	}

	private List<String> statusTarefa = Arrays.asList("A fazer", "Em execução", "Concluida");

	public List<String> getStatusTarefa() {
		return statusTarefa;
	}

}
