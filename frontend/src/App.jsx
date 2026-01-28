import { divisions } from './data/teams'
import { useState } from 'react';
import './App.css'
import RosterPanel from './RosterPanel';
import { postions } from './data/positions';
import PositionsBar from './PositionsBar';
import SearchBar from './components/SearchBar';

export default function App() {
  const [search, setSearch] = useState("");
  const [selectedTeam,setSelectedTeam] = useState(null);
  const [selectedPosition, setSelectedPosition] = useState("");
  const [players,setPlayers] = useState([]);
  const [loading,setLoading] = useState(false);
  const [error,setError] = useState(null);
  const [showSearchResults, setShowSearchResults] = useState(false)

  async function fetchPlayersByName(input) {
  const q = input.trim();
  if (!q) return;

  setLoading(true);
  setError(null);
  setSelectedTeam(null);
  setSelectedPosition("");
  setShowSearchResults(true);

  
  const parts = q.split(/\s+/);

  let url = "http://localhost:8080/api/v1/player";

  if (parts.length >= 2) {
    const first = parts[0];
    const last = parts.slice(1).join(" "); // supports last names like "Van Dyke"
    url += `?firstName=${encodeURIComponent(first)}&lastName=${encodeURIComponent(last)}`;
  } else {
    url += `?firstName=${encodeURIComponent(parts[0])}`;
  }

  try {
    const res = await fetch(url);
    if (!res.ok) throw new Error(`Error: ${res.status}`);
    const data = await res.json();
    setPlayers(data);
  } catch (e) {
    setError(e.message);
    setPlayers([]);
  } finally {
    setLoading(false);
  }
}
  
  async function fetchPlayersByTeam(teamId) {
    setLoading(true);
    setError(null);

    try {
      const res = await fetch(`http://localhost:8080/api/v1/player?team=${teamId}`);
      if(!res.ok)throw new Error(`HTTP ${res.status}`);
      const data = await res.json();
      setPlayers(data);
    } catch (e) {
      setPlayers([]);
      setError(e.message);
    } finally {
      setLoading(false);
    }
  }

  function onTeamClick(team) {
    setSearch("");
    setSelectedTeam(team);
    fetchPlayersByTeam(team.id);
  }

  async function fetchPlayerByPosition(selectedPosition){
    setLoading(true);
    setError(null);

    try{
      const res = await fetch(`http://localhost:8080/api/v1/player?position=${selectedPosition}`)
      if(!res.ok)throw new Error(`HTTP ${res.status}`);
      const data = await res.json();
      setPlayers(data);
    } catch (e) {
      setPlayers([]);
      setError(e.message);
    } finally {
      setLoading(false);
    }
  }

  async function onPositionClick(p) {
    setSearch("");
    setSelectedPosition(p.id);
    fetchPlayerByPosition(p.id);
    
  }

  async function fetchPlayerByPositionAndTeam(positionID, teamId) {
    setLoading(true);
    setError(null);

    try{
      const res = await fetch(`http://localhost:8080/api/v1/player?team=${teamId}&position=${positionID}`)
      if(!res.ok)throw new Error(`Error ${res.status}`);
      const data = await res.json();
      setPlayers(data)
    } catch (e) {
      setPlayers([]);
        setError(e.message);
    } finally {
      setLoading(false);
    }
  }

  async function onPostionAndTeamClick(position) {
    if (!selectedTeam) return;

    setSelectedPosition(position.id);
    fetchPlayerByPositionAndTeam(position.id, selectedTeam.id);
  }

  return (
    <div className='app'>
      <header className='topbar'>
        <div className='topbarMain'>
          <div className='brand'>
            <img className='brandLogo' src='/logos/NFL Logo/nfl-logo-png-transparent.png' alt='NFL Logo' />
            <div className='brandText'> NFL - Roster</div>
          </div>

          <div className='searchWrap'>
            <div className='searchLabel'>Look up players</div>
            <input
            className='searchInput'
            type='text'
            placeholder='Search by first or last name'
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === "Enter") {
                e.preventDefault();
                fetchPlayersByName(search);
              }
            }}
            />
          </div>
        </div>
          <div className='buttonsWrap'>
            {postions.map((p) => (
              <button
              key={p.id}
              type='button'
              onClick={() => onPositionClick(p)}
              title={p.name}>
                {p.id}
              </button>
            ))}
          </div>
      </header>

      <main className='page'>

      {showSearchResults && (
        <SearchBar
        players={players}
        loading={loading}
        error={error}
        onClose={ () => {
          setShowSearchResults(false);
          setError(null);
          setPlayers([]);
        }}
        />
      )}

      {selectedTeam && (
        <RosterPanel
          team={selectedTeam}
          positions={postions}
          selectedPosition = {selectedPosition}
          onPositionClick={onPostionAndTeamClick}
          players={players}
          loading={loading}
          error={error}
          onClose={() => {
            setSelectedTeam(null);
            setSelectedPosition("");
            setPlayers([]);
            setError(null);
          }}
        />
      )}

      {selectedPosition && !selectedTeam && (
  <PositionsBar
    players={players}
    positions={selectedPosition}
    loading={loading}
    error={error}
    onClose={() => {
      setSelectedPosition("");
      setPlayers([]);
      setError(null);
    }}
  />
)}

        <h2 className='pageTitle'> Teams</h2>
        <p className='pageHint'>Click teams logo to view the roster.</p>

        {divisions.map((division) => (
  <section key={division.name} className="division">
    <h1 className="divisionTitle">{division.name}</h1>

    <div className="teamsGrid">
      {division.teams.map((team) => (
          <button
        key={team.id}
        className="teamCard"
        type='button'
        onClick={() => onTeamClick(team)}
        title={team.name}
        aria-label={`View ${team.name} roster`}
        >
          <img
            src={team.logo}
            alt={team.name}
          />
          </button>
      ))}
    </div>
  </section>
))}

      </main>
    </div>
  )
}