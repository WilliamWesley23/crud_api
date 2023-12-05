package br.fepi.apicrud.resources;

// import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.fepi.apicrud.domain.Departamento;
import br.fepi.apicrud.domain.Empresa;
import br.fepi.apicrud.repository.DepartamentosRepository;
import br.fepi.apicrud.repository.EmpresasRepository;  
 
@RestController
@RequestMapping(value = "/empresas") 
public class EmpresasResources { 
	
	@Autowired
	private EmpresasRepository empresasRepository;

	@Autowired
	private DepartamentosRepository departamentosRepository; 

 
	@RequestMapping(method = RequestMethod.GET)
	public List<Empresa> listar() {
		return empresasRepository.findAll();  
	} 

	@PostMapping()
		public Empresa salvar(@RequestBody Empresa empresa){
		return empresasRepository.save(empresa);
	}

	@CrossOrigin
	@GetMapping(value = "/{id}")
	public Empresa findById(@PathVariable (value = "id")Long id){
		return empresasRepository.findById(id).orElse(null); 
	}

	@DeleteMapping(value = "/{id}")
	public void deletar(@PathVariable(value = "id")Long id){ 
		empresasRepository.deleteById(id); 
	}

	@PutMapping(value = "{id}")
	public void atualizar(@RequestBody Empresa empresa, @PathVariable("id") Long id){
		empresa.setId(id);
		empresasRepository.save(empresa);
	}

	@RequestMapping(value = "/{id}/departamento", method = RequestMethod.POST)
	public void adicionarDepartamento(@PathVariable("id") Long empresaId, @RequestBody Departamento departamento){
		Empresa empresa = new Empresa();
		empresa.setId(empresaId);

		departamento.setEmpresa(empresa); 
		departamentosRepository.save(departamento);    
	}
}
