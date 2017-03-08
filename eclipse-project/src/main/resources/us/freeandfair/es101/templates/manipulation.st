manipulation(election, voter_action) ::= <<
<div align="center">
$if(voter_action)$
<h3>The user voted using $voter_action.votingSystem.name$.<br/>

$if(voter_action.votingSystem.voteVisible)$
They voted for: 
$voter_action.vote$</h3>
$else$
You can't even see who they voted for!</h3>
$endif$

<form action="/manipulation" method="GET">
<input type="hidden" name="id" value="$voter_action.ID$" />
<input type="hidden" name="manipulate" />
$if(voter_action.votingSystem.voteManipulable)$
  $if(voter_action.votingSystem.receiptManipulable)$
    <!-- can manipulate both vote and receipt -->
    <p>You can manipulate both their vote and their $voter_action.votingSystem.receiptName$!</p>
    <p>
    <table border=0>
    <tr><th align="right">New Vote&nbsp;&nbsp</th>
    <td>$manipulation_choice(election, "vote")$</td></tr>
    <tr><th align="right">New $voter_action.votingSystem.receiptName$&nbsp;&nbsp;</th>
    <td>$manipulation_choice(election, "receipt")$</td></tr>
    </table>
    </p>
    <input type="submit" value="Manipulate!">
  $else$
    <!-- can manipulate only vote -->
    <p>You can manipulate their vote, but not their $voter_action.votingSystem.receiptName$.</p>
    <p>New Vote&nbsp;&nbsp
    $manipulation_choice(election, "vote")$</p>
    <input type="submit" value="Manipulate!">
  $endif$
$elseif(voter_action.votingSystem.receiptManipulable)$
  <!-- can manipulate only receipt -->
  <p>You can manipulate their $voter_action.votingSystem.receiptName$, but not their vote.</p>
    <p>New $voter_action.votingSystem.receiptName$&nbsp;&nbsp
    $manipulation_choice(election, "receipt")$</p>
   <input type="submit" value="Manipulate!">
$else$
  <!-- can't manipulate anything -->
  <p>You can't manipulate anything!</p>
  <input type="submit" value="OK">
$endif$

</form>
$else$
<h3>Unfortunately, there are no votes to manipulate at this time.</h3>
<h3><a href="/">OK</a></h3>
$endif$
</div>
>>