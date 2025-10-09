// 代码生成时间: 2025-10-09 19:26:56
package com.example.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api/blockchain")
public class BlockchainNodeManager {

    private final NodeService nodeService;

    public BlockchainNodeManager(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping("/nodes")
    public String getNodes() {
        try {
            return nodeService.listNodes();
        } catch (Exception e) {
            return "Error retrieving nodes: " + e.getMessage();
        }
    }

    @GetMapping("/add-node")
    public String addNode(String nodeAddress) {
        try {
            return nodeService.addNode(nodeAddress);
        } catch (Exception e) {
            return "Error adding node: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BlockchainNodeManager.class, args);
    }
}

class NodeService {

    private final Blockchain blockchain;

    public NodeService(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public String listNodes() throws Exception {
        // List all nodes in the blockchain network
        return blockchain.getNodes();
    }

    public String addNode(String nodeAddress) throws Exception {
        // Add a new node to the blockchain network
        return blockchain.addNode(nodeAddress);
    }
}

class Blockchain {

    private List<String> nodes;

    public Blockchain() {
        nodes = new ArrayList<>();
    }

    public String getNodes() {
        // Return a comma-separated list of nodes
        return String.join(",", nodes);
    }

    public String addNode(String nodeAddress) {
        // Add a node to the list if not already present
        if (!nodes.contains(nodeAddress)) {
            nodes.add(nodeAddress);
            return "Node added";
        } else {
            return "Node already exists";
        }
    }
}