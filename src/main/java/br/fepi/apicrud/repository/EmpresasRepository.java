package br.fepi.apicrud.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import br.fepi.apicrud.domain.Empresa;




public interface EmpresasRepository extends JpaRepository<Empresa, Long>{ 

} 