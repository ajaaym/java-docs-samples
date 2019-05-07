/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.appengine.pubsub;

import java.util.List;

public class PubSubHome {

  private static MessageRepository messageRepository = MessageRepositoryImpl.getInstance();
  private static int MAX_MESSAGES = 10;

  /**
   * Retrieve received messages in html.
   *
   * @return html representation of messages (one per row)
   */
  public static String getReceivedMessages() {
    List<Message> messageList = messageRepository.retrieve(MAX_MESSAGES);
    return convertToHtmlTable(messageList);
  }

  /**
   * Retrieve received claims in html.
   *
   * @return html representation of claims (one per row)
   */
  public static String getReceivedClaims() {
    List<String> claimList = messageRepository.retrieveClaims(MAX_MESSAGES);
    return convertStringsToHtmlTable(claimList);
  }

  /**
   * Retrieve received tokens in html.
   *
   * @return html representation of tokens (one per row)
   */
  public static String getReceivedTokens() {
    List<String> tokenList = messageRepository.retrieveTokens(MAX_MESSAGES);
    return convertStringsToHtmlTable(tokenList);
  }

  private static String convertToHtmlTable(List<Message> messages) {
    StringBuilder sb = new StringBuilder();
    for (Message message : messages) {
      sb.append("<tr>");
      sb.append("<td>" + message.getMessageId() + "</td>");
      sb.append("<td>" + message.getData() + "</td>");
      sb.append("<td>" + message.getPublishTime() + "</td>");
      sb.append("</tr>");
    }
    return sb.toString();
  }

  private static String convertStringsToHtmlTable(List<String> strings) {
    StringBuilder sb = new StringBuilder();
    for (String string : strings) {
      sb.append("<tr>");
      sb.append("<td>" + string + "</td>");
      sb.append("</tr>");
    }
    return sb.toString();
  }

  private PubSubHome() {}
}
