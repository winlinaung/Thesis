package application;

import controllers.BusDeperatureAndArrivalTimeController;
import controllers.AlgorithmHistoryController;
import controllers.LoginAndRegistrationController;
import controllers.ModelController;
import controllers.ResultController;
import controllers.ShowDataController;
import controllers.SignUpController;
import controllers.welcomeController;
import controllers.Step0Controller;
import controllers.Step1Controller;
import controllers.Step2Controller;
import controllers.Step3Controller;
import controllers.Step4Controller;
import controllers.Step5Controller;
import controllers.Step6Controller;
import controllers.Step7Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;

public class SceneManager {
	private Scene scene;
	public ShowDataController sdController;
	public ResultController rController;
	public ModelController mController;
	public LoginAndRegistrationController LoginAndRegController;
	public BusDeperatureAndArrivalTimeController BusDeperatureAndArrivalTimeController;
	public AlgorithmHistoryController AlgorithmHistoryController;
	public welcomeController wController;
	public Step0Controller Step0Controller;
	public Step1Controller Step1Controller;
	public Step2Controller Step2Controller;
	public Step3Controller Step3Controller;
	public Step4Controller Step4Controller;
	public Step5Controller Step5Controller;
	public Step6Controller Step6Controller;
	public Step7Controller Step7Controller;
	public SignUpController SignUpController;
	
	public SceneManager(Scene scene) {
		this.scene = scene;
	}
	public void showLoginAndRegistrationView() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("LoginAndRegistration.fxml"));
			scene.setRoot( (Parent) loader.load() );
			LoginAndRegController = loader.<LoginAndRegistrationController>getController();
			LoginAndRegController.initData(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void showBusDeperatureAndArrivalTimeView() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("BusDeperatureAndArrivalTime.fxml"));
			scene.setRoot( (Parent) loader.load() );
			BusDeperatureAndArrivalTimeController = loader.<BusDeperatureAndArrivalTimeController>getController();
			BusDeperatureAndArrivalTimeController.initData(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void showAlgorithmHistoryView() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("AlgorithmHistory.fxml"));
			scene.setRoot( (Parent) loader.load() );
			AlgorithmHistoryController = loader.<AlgorithmHistoryController>getController();
			AlgorithmHistoryController.initData(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void showWelcomeView() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/welcome.fxml"));
			scene.setRoot( (Parent) loader.load() );
			wController = loader.<welcomeController>getController();
			wController.initData(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void showModelView() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/ModelForBusScheduling.fxml"));
			scene.setRoot( (Parent) loader.load() );
			mController = loader.<ModelController>getController();
			mController.initData(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void showInputView() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/UserInputDeperatureAndArrivalTime.fxml"));
			scene.setRoot( (Parent) loader.load() );
			sdController = loader.<ShowDataController>getController();
			sdController.initData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showResultView() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/Result.fxml"));
			scene.setRoot( (Parent) loader.load() );
			rController = loader.<ResultController>getController();
			rController.initData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showSignUpView() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/SignUp.fxml"));
			scene.setRoot( (Parent) loader.load() );
			SignUpController = loader.<SignUpController>getController();
			SignUpController.initData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void showStep0View() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/Step0.fxml"));
			scene.setRoot( (Parent) loader.load() );
			Step0Controller = loader.<Step0Controller>getController();
			Step0Controller.initData(this);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void showStep1View() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/Step1.fxml"));
			scene.setRoot( (Parent) loader.load() );
			Step1Controller = loader.<Step1Controller>getController();
			Step1Controller.initData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void showStep2View() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/Step2.fxml"));
			scene.setRoot( (Parent) loader.load() );
			Step2Controller = loader.<Step2Controller>getController();
			Step2Controller.initData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void showStep3View() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/Step3.fxml"));
			scene.setRoot( (Parent) loader.load() );
			Step3Controller = loader.<Step3Controller>getController();
			Step3Controller.initData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void showStep4View() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/Step4.fxml"));
			scene.setRoot( (Parent) loader.load() );
			Step4Controller = loader.<Step4Controller>getController();
			Step4Controller.initData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void showStep5View() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/Step5.fxml"));
			scene.setRoot( (Parent) loader.load() );
			Step5Controller = loader.<Step5Controller>getController();
			Step5Controller.initData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void showStep6View() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/Step6.fxml"));
			scene.setRoot( (Parent) loader.load() );
			Step6Controller = loader.<Step6Controller>getController();
			Step6Controller.initData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void showStep7View() {
		try {
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("/application/Step7.fxml"));
			scene.setRoot( (Parent) loader.load() );
			Step7Controller = loader.<Step7Controller>getController();
			Step7Controller.initData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
