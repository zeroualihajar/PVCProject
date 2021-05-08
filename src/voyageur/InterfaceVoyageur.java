package voyageur;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Outils.PVC;
import Outils.Ville;
import jade.gui.GuiEvent;

public class InterfaceVoyageur extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	public JPanel mapPanel;
	public MapPanel map;

	private AgentVoyageur agentVoyageur;
	private Ville villes[];
	private PVC pvc = new PVC();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceVoyageur frame = new InterfaceVoyageur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public InterfaceVoyageur() {
		setTitle("PVC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 532);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 184, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mapPanel = new JPanel();
		mapPanel.setBackground(new Color(255, 245, 238));
		mapPanel.setBounds(10, 16, 400, 478);
		contentPane.add(mapPanel);
		mapPanel.setLayout(null);
		
				map = new MapPanel();
				map.setBounds(0, 0, 401, 478);
				mapPanel.add(map);
				map.repaint();

		JButton getCheminBtn = new JButton("Calculer");
		getCheminBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("On va commencer le calcul :)");
				GuiEvent gev = new GuiEvent(contentPane.getUI(), 1);
				List<Ville> villes = map.getVilles();
				java.util.Map<String, Object> params = new HashMap<>();
				params.put("VillesV1", villes);

				gev.addParameter(params);
				agentVoyageur.onGuiEvent(gev);
			}
		});
		getCheminBtn.setBounds(483, 16, 113, 39);
		contentPane.add(getCheminBtn);

		JButton undoBtn = new JButton("Undo");
		undoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPvc().EtapePrecedente();
				int i = getPvc().getEtapeCourante();
				System.out.println(i + "cc");
				map.setEtapeActuelle(i);
				map.repaint();
			}
		});
		undoBtn.setBounds(483, 124, 113, 33);
		contentPane.add(undoBtn);

		JButton redoBtn = new JButton("Redo");
		redoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getPvc().getEtapeCourante() < villes.length - 1) {
					map.setEtapeActuelle(map.getEtapeActuelle() + 1);
				}
				map.setEtapeActuelle(map.getEtapeActuelle() + 1);
				System.out.println(map.getEtapeActuelle() + "cc");
				map.repaint();
			}
		});
		redoBtn.setBounds(483, 80, 113, 33);
		contentPane.add(redoBtn);

		/*
		 * mapPanel.addMouseListener(new MouseAdapter() {
		 * 
		 * @Override public void mouseClicked(final MouseEvent e) { String retour =
		 * JOptionPane.showInputDialog("Saisir le nom de la ville : "); final int x =
		 * e.getX(); final int y = e.getY();
		 * 
		 * try { imageVille = ImageIO.read(new File("images/" + "favicon.ico")); //
		 * this.VilleImageCenter = new Dimension(this.VilleImage.getWidth() / 2,
		 * this.VilleImage.getHeight() / 2); } catch (final IOException ex) { imageVille
		 * = null; // this.VilleImageCenter = new Dimension(0, 0); }
		 * 
		 * villes.add(new Ville(villes.size() + 1, retour, x, y, false));
		 * System.out.println("Ville : " + retour + "\n" + "(x,y) : (" + e.getX() + ", "
		 * + e.getY() + ")\n" + "Nbre de villes : " + villes.size());
		 * 
		 * paintVilles(); repaint(); } });
		 */
	}

	public Ville[] getVilles() {
		return villes;
	}

	public void setVilles(Ville[] villes) {
		this.villes = villes;
	}

	public PVC getPvc() {
		return pvc;
	}

	public void setPvc(PVC pvc) {
		this.pvc = pvc;
	}

	public AgentVoyageur getAgentVoyageur() {
		return agentVoyageur;
	}

	public void setAgentVoyageur(AgentVoyageur agentVoyageur) {
		this.agentVoyageur = agentVoyageur;
	}
}
