package tois.control;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import tois.entity.Conta;
import tois.entity.Transacao;

public class AcessoListener implements PhaseListener {
private static final long serialVersionUID = -2332229583990843261L;

@Override
public void afterPhase(PhaseEvent ev) {
	FacesContext fc = ev.getFacesContext();
	String pagina = fc.getViewRoot().getViewId();
	if (!"/Home.xhtml".equals(pagina)) { 
		Application app = fc.getApplication();
		
		Conta user = app.evaluateExpressionGet(fc, 
				"#{ContaMB.usuarioAtual}", Conta.class);
		if (user == null) { 
			NavigationHandler nav = app.getNavigationHandler();
			nav.handleNavigation(fc, null, "login?faces-redirect=true");
			fc.renderResponse();
		}
	}
}

@Override
public void beforePhase(PhaseEvent ev) {
//	System.out.println(ev.getPhaseId().getName() +	"beforePhase");
}

@Override
public PhaseId getPhaseId() {
	//return PhaseId.RESTORE_VIEW;
	return PhaseId.ANY_PHASE;
}

}