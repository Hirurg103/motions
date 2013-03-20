import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.GridLayout;
public class Main extends JFrame {
	public Main() {
		super("TreeSelectionModes");
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		//создаем модель нашего дерева 
		TreeModel model =  createTreeModel(); // дерево с одиночным режимом выделения 
		JTree tree1 = new JTree();

		tree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION); 
		//дерево с выделением непрерывными интервалами JTree
		JTree tree2 = new JTree(model);
		tree2.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION); 
		//модель выделения можно хранить и отдельно
		TreeSelectionModel selModel = new DefaultTreeSelectionModel();
		selModel.setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		JTree tree3 = new JTree(model);
		tree3.setSelectionModel(selModel); // следим за выделением в последнем дереве 
		tree3.addTreeSelectionListener(new SelectionListener()); // размещаем деревья в панели JPanel
		contents = new JPaneKnew GndLayoutd. 3)) contents,
add (new JScrollPane(treeD): contents.add(new
JScrol1Pane(tree2)); contents.add(new
JScrol1Pane(tree3)): getContentPaneO.add(contents): //
добавляем текстовое поле getContentPane().add(
new JScrol1 Pane(log). "South"): //
выводим окно на экран setSize(500.
300): setVisible(true):
}

	// текстовое поле для информации private JTextArea
	log = new JTextArea(5, 20): // листья дерева храним в массивах 
	private String[] langs = { "Java", "C++", "C#"}; 
	private String[] ides = { "Visual Studio", "Eclipse", "NetBeans" }; // создание несложной модели дерева
	private TreeModel createTreeModel() { 
		// корень нашего дерева 
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Создание кода"); 
		//основные ветви 
		DefaultMutableTreeNode lang = new DefaultMutableTreeNode("Языки");
		DefaultMutableTreeNode ide = new DefaultMutableTreeNode("Среды");
		root.add(lang); root.add(ide); // присоединяем листья
		for (int i=0; i < langs.length; i++) { 
			lang.add(new DefaultMutableTreeNode(langs[i])); 
			ide.add(new DefaultMutableTreeNode(ides[i]));
		}
		// создаем стандартную модель
		return new DefaultTreeModel(root);
	}
// этот слушатель следит за изменением выделения
class SelectionL implements TreeSelectionListener {
public void valueChanged(TreeSelectionEvent e) { //
получаем источник события - дерево JTree tree =
(JTree)e.getSourceO; // добавленные/удаленные пути
TreePath[] paths = e.getPathsO; log.append("Изменено
путей: " +
paths.length + "\n"); // выделенные элементы и их
номера строк TreePathC] selected =
tree.getSelectionPathsO: int[] rows =
tree.getSelectionRowsl): II последние элементы в
пути for (int i=0: i<selected.length; i++) {
log.appendC"Выделен: " +
selectedCi].getLastPathComponent() + " на строке:
" + rows[i] + "\n");
}
// полная информация о пути 8 дереве
TreePath path = selected[0]; Object[]
nodes = path.getPathO: for (int i=0:
i<nodes.length: i++) {
J J путь состоит из узлов
DefaultMutableTreeNode node =
(DefaultMutableTreeNode)nodes[i]:
log.append("Отрезок пути " + i + " : " +
node.getUserObjectO + " "): }Выделение
419
log.append("\n");
} } public static void main(String[] args) {
new TreeSelectionModesO: }