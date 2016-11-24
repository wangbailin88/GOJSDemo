 function init2(dataJson) {
	    if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
	    var $ = go.GraphObject.make;  // for conciseness in defining templates in this function
		//����һ��gojs����
	    myDiagram =
	      $(go.Diagram, "myDiagramDiv",  // must be the ID or reference to div
	        { initialContentAlignment: go.Spot.Center,
	    	  "undoManager.isEnabled": true//, // enable undo & redo
	    	  //"grid.gridCellSize":new go.Size(20,20)
	    	  });

	    // define all of the gradient brushes
	    var graygrad = $(go.Brush, "Linear", { 0: "#F5F5F5", 1: "#F1F1F1" });
	    var bluegrad = $(go.Brush, "Linear", { 0: "#CDDAF0", 1: "#91ADDD" });
	    var yellowgrad = $(go.Brush, "Linear", { 0: "#FEC901", 1: "#FEA200" });
	    var lavgrad = $(go.Brush, "Linear", { 0: "#EF9EFA", 1: "#A570AD" });
	   	//ͼƬ
	    function findHeadShot(image) {
	        return "images/nodeImage/"+image;
	      }
	    //������ʽ����С��
	    function textStyle() {
	        return { font: "9pt  Segoe UI,sans-serif", stroke: "white" };
	      }
	    //�����ڵ����
	    // define the Node template for non-terminal nodes
	    myDiagram.nodeTemplate =
	    	
	    	$(go.Node, "Auto",
	    			{isShadowed: true},
	    	        // define the node's outer shape
	    	        $(go.Shape, "RoundedRectangle",
	    	          { fill: graygrad, stroke: ""},
	    	          new go.Binding("fill", "color",function(v){
	    	        	  return v;
	    	          })),
	    	      
	    	        //����һ��panel����
	    	        $(go.Panel, "Horizontal",
	    	        	//ͼ��
	    	          $(go.Picture,
	    	            {
	    	              name: 'Picture',
	    	              minSize:new go.Size(15,0),
	    	              maxSize: new go.Size(50, 50),
	    	              margin: new go.Margin(0, 0, 0, 5),
	    	            },
	    	            new go.Binding("source", "image", function(v){
	    	            		return "images/nodeImage/"+v;
	    	            })),
	    	          // define the panel where the text will appear
	    	          //����һ��table����
	    	          $(go.Panel, "Table",
	    	            {
	    	        	  //minSize: new go.Size(1, 1),
	    	              maxSize: new go.Size(500, 999),
	    	              //margin: new go.Margin(0, 0, 0, 0),
	    	              defaultAlignment: go.Spot.Left
	    	            },
	    	            $(go.RowColumnDefinition, { column: 2, width: 4 }),
	    	             // $(go.TextBlock, "", textStyle(),
	    	    	          // { row: 0, column: 0 }),
	    	    	              $(go.TextBlock, textStyle(),
	    	    	               {
	    	    	               row: 0, column: 1, columnSpan: 2,
	    	    	                editable: true, isMultiline: false,
	    	    	                //minSize: new go.Size(1, 1),
	    	    	                margin: new go.Margin(0, 0, 0, 0),
	    	    	               },
	    	    	           new go.Binding("text", "name",function(v){
	    	    	        	   if(v==null || v== ""){
	    	    	        		   console.log(myDiagram.nodeTemplate);
	    	    	        		   return;
	    	    	        	   }
	    	    	        	   return v;
	    	    	           })),
	    	           // $(go.TextBlock, "", textStyle(),
	    	              //{ row: 1, column: 0 }),
	    	            $(go.TextBlock, textStyle(),
	    	              {
	    	                row: 1, column: 1, columnSpan: 2,
	    	                editable: true, isMultiline: false,
	    	                //minSize: new go.Size(1, 1),
	    	                margin: new go.Margin(5, 0, 0, 0),
	    	              },
	    	              new go.Binding("text", "value",function(v){
	    	            	  if(v!="" && v!=null){
	    	            		  return v;
	    	            	  }
	    	            	  return "";
	    	              }))
	    	           //$(go.TextBlock, textStyle(),
	    	             // { row: 2, column: 0 },
	    	             // new go.Binding("text", "key", function(v) {
	    	            	//  return "ID:"+v;
	    	            	//  }
	    	             // )
	    	             // ),
	    	            //$(go.TextBlock, textStyle(),
	    	              //{ name: "boss", row: 2, column: 3, }, // we include a name so we can access this TextBlock when deleting Nodes/Links
	    	              //new go.Binding("text", "parent", function(v) {return "parent:"+v;})),
	    	          )  // end Table Panel
	    	        ) // end Horizontal Panel
	    	      );  // end Node
	    	     
	    			
			//����������
	    // define the Link template
	        myDiagram.linkTemplate =
	            $(go.Link, go.Link.Orthogonal,
	              { corner: 30, relinkableFrom: true, relinkableTo: true },
	              $(go.Shape, { strokeWidth: 1, stroke: "#00a4a4" })
	              );  // the link shape
	     //�ڵ�����
	    // create the model for the double tree
	    myDiagram.model = go.Model.fromJson(dataJson);
	    doubleTreeLayout(myDiagram);
	  }
	//����doubletree����
	  function doubleTreeLayout(diagram) {
	    // Within this function override the definition of '$' from jQuery:
	    var $ = go.GraphObject.make;  // for conciseness in defining templates
	    diagram.startTransaction("Double Tree Layout");
		//����ڵ�λ�����ڵ�ķ���
	    // split the nodes and links into two Sets, depending on direction
	    var leftParts = new go.Set(go.Part);   //��
	    var rightParts = new go.Set(go.Part);//��
	    var topParts = new go.Set(go.Part); //��
	    var belowParts =  new go.Set(go.Part);//��
	    separatePartsByLayout(diagram, leftParts, rightParts,topParts,belowParts);
	    //�����ڵ�����ķ���
	    //��
	    var layoutLeft =
	      $(go.TreeLayout,
	        { angle: 180,
	    	  arrangement: go.TreeLayout.ArrangementFixedRoots,
	          setsPortSpot: false,
	    	  layerSpacing: 70,//�ڵ��ļ����
	    	  alternateAngle: 522,
	    	  alternateLayerSpacing: 1000,
	    	  alternateAlignment: go.TreeLayout.AlignmentBus,
              alternateNodeSpacing: 500,
              //treeStyle:go.TreeLayout.StyleLastParents
	          
	         
	          }
	      );
		//��
	    var layoutRight =
	      $(go.TreeLayout,
	        { angle: 0,
	    	  layerSpacing: 70,//�ڵ��ļ����
	          arrangement: go.TreeLayout.ArrangementFixedRoots,
	          setsPortSpot: false });
		//��
	    var layoutTop =
		      $(go.TreeLayout,
		        { angle: 270,
		    	  //layerSpacing: 200,//�ڵ��ļ����
		          arrangement: go.TreeLayout.ArrangementFixedRoots,
		          setsPortSpot: false });
		//��
	    var layoutBelow =
		      $(go.TreeLayout,
		        { angle: 90,
		    	  layerSpacing: 50,//�ڵ��ļ����
		          arrangement: go.TreeLayout.ArrangementFixedRoots,
		          setsPortSpot: false });
	    layoutTop.doLayout(topParts);
	    layoutLeft.doLayout(leftParts);
	    layoutRight.doLayout(rightParts);
	    layoutBelow.doLayout(belowParts);
	    diagram.commitTransaction("Double Tree Layout");
	  }
	  

	  function separatePartsByLayout(diagram, leftParts, rightParts,topParts,belowParts) {
	    var root = diagram.findNodeForKey("root");
	    if (root === null) return;
	    // the ROOT node is shared by both subtrees!
	    leftParts.add(root);
	    rightParts.add(root);
	    topParts.add(root);
	    belowParts.add(root);
	    // look at all of the immediate children of the ROOT node
	    root.findTreeChildrenNodes().each(function(child) {
	    	var root = diagram.findNodeForKey("root");
	        if (root === null) return;
	        // the ROOT node is shared by both subtrees!
	        leftParts.add(root);
	        rightParts.add(root);
	        topParts.add(root);
	        belowParts.add(root);
	        // look at all of the immediate children of the ROOT node
	        root.findTreeChildrenNodes().each(function(child) {
	            // in what direction is this child growing?
	            var dir = child.data.dir;
	            var coll = null;
	            if(dir=="left"){
	            	coll = leftParts;
	            }
	            //else if(dir=="top"){
	            	//coll = topParts;
	            //}else if(dir == "right"){
	            	//coll = rightParts;
	            	//coll = rightParts;
	            //}else if(dir == "below"){
	            	//coll = belowParts;
	            	//coll = rightParts;
	            //}
	            else{
	            	coll = rightParts;
	            }
	            // add the whole subtree starting with this child node
	            coll.addAll(child.findTreeParts());
	            // and also add the link from the ROOT node to this child node
	            coll.add(child.findTreeParentLink());
	          });
	      }
	    
	    );
	  }