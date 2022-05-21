package vboot.module.bpm.task.hist;


import org.springframework.data.jpa.repository.JpaRepository;

public interface BpmTaskHistRepo extends JpaRepository<BpmTaskHist,String> {


}