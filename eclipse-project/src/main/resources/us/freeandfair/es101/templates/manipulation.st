manipulation(election, voter_action) ::= <<

$if(voter_action)$
<h3>The voter used: $voter_action.votingSystem.name$<br/>

$if(voter_action.votingSystem.voteVisible)$
They voted for: 
$voter_action.vote$</h3>
$else$
You can't see who they voted for!</h3>
$endif$

<div align="center">
$if(voter_action.votingSystem.voteManipulable)$
  <h3>You can hack their vote!</h3>
  <table>
  $election.candidates:{ candidate |
    <tr><td align="center">
      <button type="button" style="width:100%"
       onclick="window.location.href='/manipulation?id=$voter_action.ID$&manipulate&vote=$candidate$'">$candidate$</button></td></tr>
  }$
  </table>
$else$
  <h3>You can't hack their vote!</h3>
  <div align="center">
  <button type="big_button"
   onclick="window.location.href='/'">OK</button>
$endif$
</div>

$else$
<div align="center">
<h3>Unfortunately, there are no votes to hack at this time.</h3>
<button type="big_button"
 onclick="window.location.href='/'">OK</button>
</div>
$endif$
>>