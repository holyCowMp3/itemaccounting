package viti.kaf22.itemaccounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viti.kaf22.itemaccounting.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    Ticket findByQrBase64(String base64);

}
