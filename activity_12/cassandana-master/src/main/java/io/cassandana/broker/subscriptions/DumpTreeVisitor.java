/*
 *  Copyright 2019 Mohammad Taqi Soleimani
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 */
package io.cassandana.broker.subscriptions;

import io.netty.util.internal.StringUtil;

class DumpTreeVisitor implements CTrie.IVisitor<String> {

    String s = "";

    @Override
    public void visit(CNode node, int deep) {
        String indentTabs = indentTabs(deep);
        s += indentTabs + (node.token == null ? "''" : node.token.toString()) + prettySubscriptions(node) + "\n";
    }

    private String prettySubscriptions(CNode node) {
        if (node instanceof TNode) {
            return "TNode";
        }
        if (node.subscriptions.isEmpty()) {
            return StringUtil.EMPTY_STRING;
        }
        StringBuilder subScriptionsStr = new StringBuilder(" ~~[");
        int counter = 0;
        for (Subscription couple : node.subscriptions) {
            subScriptionsStr
                .append("{filter=").append(couple.topicFilter).append(", ")
                .append("qos=").append(couple.getRequestedQos()).append(", ")
                .append("client='").append(couple.clientId).append("'}");
            counter++;
            if (counter < node.subscriptions.size()) {
                subScriptionsStr.append(";");
            }
        }
        return subScriptionsStr.append("]").toString();
    }

    private String indentTabs(int deep) {
        StringBuilder s = new StringBuilder();
        if (deep > 0) {
            s.append("    ");
            for (int i = 0; i < deep - 1; i++) {
                s.append("| ");
            }
            s.append("|-");
        }
        return s.toString();
    }

    @Override
    public String getResult() {
        return s;
    }
}
