package fr.spring.eBoutique.project.control;


import fr.spring.eBoutique.project.model.Commande;
import fr.spring.eBoutique.project.service.ICommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CommandeControl implements HandlerExceptionResolver {


    private ICommandeService commandeService;



    @GetMapping(path= "id")
    public Commande getCommande(@PathVariable("id") Long idUtilisatuer) {
        return commandeService.getCommande(idUtilisatuer);
    }


    @PostMapping
    public Commande addCommande(@RequestBody Commande commande) {
        return commandeService.addCommande(commande);
    }


    @GetMapping (path="{idUtilisatuer}/Commande")
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return null;
    }
}
