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

import jade.gui.GuiEvent;
import Outils.Ville;

public class InterfaceVoyageur extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	public JPanel mapPanel;
	public MapPanel map;
	
	private AgentVoyageur agentVoyageur;

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
		setBounds(100, 100, 644, 412);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mapPanel = new JPanel();
		mapPanel.setBackground(new Color(255, 245, 238));
		mapPanel.setBounds(10, 62, 500, 300);
		contentPane.add(mapPanel);
		mapPanel.setLayout(null);
		
		map = new MapPanel();
		map.setBounds(245, 5, 10, 10);
		map.setBounds(0, 0, map.WIDTH_MAP, map.HEIGHT_MAP);
        mapPanel.add(map);
        map.repaint();

		JButton getCheminBtn = new JButton("Calculer");
		getCheminBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("On va commencer le calcul :)");
				GuiEvent gev = new GuiEvent(contentPane.getUI(), 1);
				List<Ville> villes = map.villes;
				java.util.Map<String, Object> params = new HashMap<>();
				params.put("VillesV1", villes);

				gev.addParameter(params);
				agentVoyageur.onGuiEvent(gev);
			}
		});
		getCheminBtn.setBounds(10, 13, 113, 39);
		contentPane.add(getCheminBtn);

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
}
